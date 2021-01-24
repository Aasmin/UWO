##
# TA: Aasminpreet Singh Kainth
# This is the Country class that holds the information about a single country, including associated
# getter and setter methods.

class Country:

    #
    # This is the constructor for the Country class that initializes instances variables.
    # @param name is the name of the country.
    # @param pop is the population of the country.
    # @param area is the area of the country.
    # @param continent is the continent in which the country is located.
    def __init__(self, name = "", pop = None, area = None, continent = ""):

        #Initialize the instance variables.
        self._name = name
        self._population = pop
        self._area = area
        self._continent = continent

    #
    # This method returns the name of the country.
    # @return self._name is the name of the country.
    def getName(self):

        # return the country name
        return self._name

    #
    # This method returns the population of the country.
    # @return self._population is the population of the country.
    def getPopulation(self):

        # return the country population
        return self._population

    #
    # This method returns the area of the country.
    # @return self._area is the area of the country.
    def getArea(self):

        #return the country area
        return self._area

    #
    # This method returns the continent of the country.
    # @return self._continent is the continent of the country.
    def getContinent(self):

        # return the continent the country is found in
        return self._continent

    #
    # This method sets the population of the country.
    # @param population is the new value to set the population to.
    def setPopulation(self, population):

        #set population instance variable
        self._population = population

    #
    # This method sets the area of the country.
    # @param area is the new value to set the area to.
    def setArea(self, area):

        #set area instance variable
        self._area = area

    #
    # This method sets the continent of the country.
    # @param continent is the new value to set the continent to.
    def setContinent(self, continent):

        #set continent instance variable
        self._continent = continent

    #
    # This is the method to represent the country class
    # @return A string sequence that summarizes the country information
    def __repr__(self):

        # return the string that includes name, population, area and continent (if applicable)
        return self._name + " (pop: "+str(self._population) + ", size: " + str(self._area) + ") in " + self._continent


