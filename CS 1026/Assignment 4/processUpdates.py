##
# TA: Aasminpreet Singh Kainth
# This is the processUpdates module that contains the methods necessary to create the CountryCatalogue object
# and update it with new files of information.

# import the CountryCatalogue class
from catalogue import CountryCatalogue

#
# This function takes in the country information file and the update file. It will create a CountryCatalogue
# using the appropriate information and update it according to the file. It will then print to the output.txt file.
# @param cntryFileName is the file that contains the country data.
# @param updateFileName is the file that contains the update information.
# @return returns True if the update is successful, or False if it is not.
def processUpdates(cntryFileName, updateFileName):

    # userInput keeps the loop progressing, failed determines if file has been successfully opened.
    userInput = "N"
    failed = True

    # Loop progresses until user quits or catalogue has been successfully created
    while userInput == "N":

        # Try to open the country data file
        try:

            # Open the country data file in read mode
            cntryFile = open(cntryFileName, "r")

            # Create a catalogue of the country objects in the file
            catalogue = CountryCatalogue(cntryFileName)

            # File has been successfully been opened, set variable accordingly.
            failed = False
            # Set variable to exit loop
            userInput = "Y"

        # If the file couldn't be opened, prompt the user to either continue or quit
        except IOError:

            userInput = str(input("The country file doesn't exist. Enter N to continue, or Y to quit: "))

            # If the user decides to continue, ask them for a new country data file name.
            if userInput == "N":
                cntryFileName = str(input("Enter the new country file name: "))

    # If the user quits before country data is implemented into a catalogue object, return False.
    if failed:
        return failedUpdate()

    # Reset the loop progression and file opening variables
    userInput = "N"
    failed = True

    # Loop progresses until user quits or update has successfully occured.
    while userInput == "N":

        # Try to open the update file
        try:

            # Open the update file in read mode
            updateFile = open(updateFileName, "r")

            # Go through every line in the update file.
            for line in updateFile:

                # Create a list of strings of the read line, divided by the semi-colon
                newLine = line.split(";")

                # If the country name is not found as a key in the catalogue, add a new entry into the catalogue
                # based on the country name.
                if newLine[0] not in catalogue.getDictionary():
                    catalogue.addCountry(newLine[0])

                # Loop through the list of strings in the line
                for i in range(len(newLine)):

                    # Strip the string of spaces before and after
                    newLine[i] = newLine[i].strip()

                    # This is the population update indicator
                    if newLine[i].startswith("P="):

                        # Try to update the population value by sending the string after the "P=" to the setter
                        # method.
                        try:
                            catalogue.setPopulationOfCountry(newLine[0], newLine[i][2:])
                        # If the value has bad values, notify the user and proceed.
                        except ValueError:
                            print("Sorry, bad value encountered. The update " + newLine[i]+" was not completed.")

                    # This is the area update indicator
                    if newLine[i].startswith("A="):

                        # Try to update the area value by sending the string after the "A=" to the setter
                        # method.
                        try:
                            catalogue.setAreaOfCountry(newLine[0], newLine[i][2:])
                        # If the value has bad values, notify the user and proceed.
                        except ValueError:
                            print("Sorry, bad value encountered. The update " + newLine[i]+" was not completed.")

                    # This is the continent update indicator
                    if newLine[i].startswith("C="):

                        # Try to update the continent by sending the string after the "C=" to the setter
                        # method.
                        try:
                            catalogue.setContinentOfCountry(newLine[0], newLine[i][2:])
                        # If the value has bad values, notify the user and proceed.
                        except ValueError:
                            print("Sorry, bad value encountered. The update " + newLine[i]+" was not completed.")

            # Save the catalogue information with the updates to the output.txt file.
            catalogue.saveCountryCatalogue("output.txt")

            # Return True to exit the loop and notify successful processing.
            return True

        # If the update file was unable to open, ask user if they want to continue or quit.
        except IOError:
            userInput = str(input("The update file does not exist. Enter N to continue, or Y to quit: "))

            # If the user continues, ask them for a new update file name.
            if userInput == "N":
                updateFileName = str(input("Enter the new update file name: "))

    # If user quits without successful updating, return False.
    if failed:
        return failedUpdate()

#
# This function returns False after writing unsuccessful processing message to output.txt file
# @return returns False
def failedUpdate():

    # Open the output file, and write appropriate message
    outputFile = open("output.txt", "w")
    outputFile.write("Update Unsuccessful\n")

    # Return False
    return False

