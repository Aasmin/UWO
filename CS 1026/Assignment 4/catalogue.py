##
# TA: Aasminpreet Singh Kainth
# This is the CountryCatalogue class that builds a dictionary data structure to hold the information about
# various Country objects from a file.

#import the Country class and locale module
from country import Country
import locale

class CountryCatalogue:

    #set locale to match current settings
    locale.setlocale(locale.LC_ALL,'en_US.UTF-8')

    #
    # This is the constructor for the CountryCatalogue class that sets up the dictionary of Country objects.
    # @param countryFile is the file name that contains the country information.
    def __init__(self, countryFile):

        # initialize the dictionary as instance variable
        self._countryCat = {}

        # open the file given
        file = open(countryFile,"r",encoding="utf-8")

        # skip the first line (header)
        next(file)

        # loop through every line in the file
        for line in file:

            # remove leading and trailing white space
            currentLine = line.strip()
            # split the line into a list of strings based on the | character
            currentLine = currentLine.split("|")

            # if the population value is not empty, convert the string with commas into an integer.
            if len(currentLine[2])!=0:
                currentLine[2] = int(locale.atoi(currentLine[2]))

            # if the area value is not empty, convert the string with commas into an integer.
            if len(currentLine[3])!=0:
                currentLine[3] = int(locale.atoi(currentLine[3]))

            # Initialize the Country object using the given values from the file line, and add it to the
            # dictionary using the country name as the Key.
            self._countryCat[currentLine[0]] = Country(currentLine[0], currentLine[2], currentLine[3], currentLine[1])

        # Close the opened file
        file.close()

    #
    # This method changes the population value of a country.
    # @param name is the name of the country which the population applies to.
    # @param population is the new population value.
    def setPopulationOfCountry(self, name, population):

        # If the population value is in the form of a string with commas, convert it to an integer
        if "," in str(population):
            population = locale.atoi(population)

        # Search the dictionary for the country object, and change the population value using the setter method.
        self._countryCat[name].setPopulation(int(population))

    #
    # This method changes the area value of a country.
    # @param name is the name of the country which the area applies to.
    # @param area is the new area value.
    def setAreaOfCountry(self, name, area):

        # If the area value is in the form of a string with commas, convert it to an integer
        if "," in str(area):
            area = locale.atoi(area)

        # Search the dictionary for the country object, and change the area value using the setter method.
        self._countryCat[name].setArea(int(area))

    #
    # This method changes the continent of a country.
    # @param name is the name of the country which the continent applies to.
    # @param continent is the new continent string.
    def setContinentOfCountry(self, name, continent):

        # Search the dictionary for the country object, and change the continent using the setter method.
        self._countryCat[name].setContinent(continent)

    #
    # This method checks to see if a country object is found in the catalogue.
    # @param country is a Country object to search for.
    # @return returns the Country object if found, or None if not found.
    def findCountry(self, country):

        # If the name of the Country object is a key in the catalogue, return the Country object.
        # Otherwise, return None.
        if country.getName() in self._countryCat:
            return country
        else:
            return None

    #
    # This method adds a new Country object to the catalogue, IF the country doesn't already exist in the
    # catalogue.
    # @param countryName is the name of the country
    # @param pop is the population of the country
    # @param area is the area of the country
    # @param cont is the continent of the country
    # @return returns True if the operation is successful, False if it is not.
    def addCountry(self, countryName, pop = None, area = None, cont=""):

        # Convert the area value into an integer if it is a string with commas
        if "," in str(area):
            area = locale.atoi(area)

        # Convert the population value into an integer if it is a string with commas
        if "," in str(pop):
            pop = locale.atoi(pop)

        # If the country does not exist in the catalogue, add a new Country object using the values given.
        # Return True in this case. If it's already in the catalogue, return False.
        if countryName not in self._countryCat:
            self._countryCat[countryName] = Country(countryName, pop, area, cont)
            return True
        else:
            return False

    #
    # This method prints all the information of the country objects held in the catalogue.
    def printCountryCatalogue(self):

        # Loop through the entire catalogue and use the __repr__ method to print information on each country.
        for country in self._countryCat:
            print(self._countryCat[country])

    #
    # This method saves all the information held in the catalogue to a file according to specifications.
    # @param fname is the file name to save the information to.
    def saveCountryCatalogue(self, fname):

        # initialize count to 0, to keep track of number of country objects written to the file
        count = 0

        # Try to open the file of the given file name and write according to specifications
        try:

            # Open a file with the given name in the "write" mode
            file = open(fname, "w")

            # Create a list of sorted country names, in alphabetical order
            sortedCountries = sorted(self._countryCat.keys(), key = lambda country:country.lower())

            # Write the header of the file
            file.write("Country|Continent|Population|Area\n")

            # Loop through the country names in alphabetical order, using them as keys to the catalogue.
            # Therefore, you're looping through the catalogue in alphabetical order.
            for country in sortedCountries:

                # If the population value of the country is not None or an empty string, convert it into a
                # string with commas at every thousandths position. Otherwise, make it an empty string.
                if self._countryCat[country].getPopulation() != None and self._countryCat[country].getPopulation() != "":
                    population = locale.format_string("%d", self._countryCat[country].getPopulation(), grouping=True)
                else:
                    population = ""

                # If the area value of the country is not None or an empty string, convert it into a
                # string with commas at every thousandths position. Otherwise, make it an empty string.
                if self._countryCat[country].getArea() != None and self._countryCat[country].getArea() != "":
                    area = locale.format_string("%d", self._countryCat[country].getArea(), grouping=True)
                else:
                    area = ""

                # Write the information of the country object to the file according to the specifications on
                # the assignment.
                file.write(self._countryCat[country].getName()+"|"+self._countryCat[country].getContinent()+"|"+
                           population+"|"+area+"\n")

                # Accumulate 1 on the counter
                count+=1

            # If at least 1 Country object information was written to the file, return the number of objects
            # whose information was saved. Otherwise, return -1 for unsuccessful operation.
            if count != 0:
                return count
            else:
                return -1

        # If problem writing to this file, return -1 since operation was unsuccessful.
        except IOError:
            return -1

    #
    # This method returns the catalogue.
    # @return self._countryCat is the instance of the CountryCatalogue variable.
    def getDictionary(self):
        return self._countryCat

