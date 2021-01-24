##
# TA: Aasminpreet Singh Kainth
# This program computes the volumes for any number of cubes, pyramids and ellipsoids given user input. The program
# will then summarize the calculated volumes, sort them in increasing order based on shape, and the write the results
# to a text file.
#

# Define the main method.
def main():

    # Import the self-created "volumes" module and the given "summarize" module.
    import volumes
    import summarize

    validInput = ["cube", "c", "pyramid", "p", "ellipsoid", "e", "quit", "q"]  # All valid user inputs when asking for shape
    valid = False  # Used to keep while loops running until valid input
    isShape = True # Used to see if user input is a shape or quit
    index = 0  # Tracks the index of user input within "validInput" list

    # Lists of all the shapes to keep track of calculated volumes
    cubeVolumes = []
    pyramidVolumes =[]
    ellipsoidVolumes = []

    # Introduction
    print("~Welcome to the Volume Calculator.~")
    print("")
    print("")

    # While loop to ask for a valid test case number
    while not valid:

        testCase = input("Enter the test case number: ")  # Ask for test case number

        # If the input is an integer, set the testCase variable to input and change 'valid' to True to exit while loop
        if testCase.isnumeric():
            testCase = int(testCase)
            valid = True
        else:
            print("Sorry, the test case must be a number.") # If the input is not an integer, print message and loop again

    valid = False  # Reset 'valid' for next while loop

    # While loop that keeps running until the user enters 'quit' or 'q'
    while isShape:

        # While loop that asks for user input, calculates volume (if necessary) and keeps running until quit input.
        while not valid:

            shape = str(input("Please enter a shape: "))  # Ask for input
            shape = shape.lower()  # Convert to lower case

            # If the input is within the list of valid inputs, track down the index within the list and set 'valid'
            # to true. This allows the program to exit the while loop.
            if shape in validInput:
                index = validInput.index(shape)
                valid = True
            else:
                print("Sorry, your input is invalid.")  # Print error message for invalid input, and loop again
                print("")

        # If the user input was "cube" or "c", perform necessary actions.
        if index in range(0,2):

            sideLength = int(input("Enter the side length of the cube: "))  # Ask the user for side length
            currentVolume = volumes.cubeVolume(sideLength)  # Calculate the volume by sending sidelength to method
                                                            # "cubeVolume" within 'volumes' module.
            cubeVolumes.append(currentVolume) # Add the volume to the cubes list

        # If the user input was "pyramid" or "p", perform necessary actions.
        elif index in range(2,4):

            baseLength = int(input("Enter the base length of the pyramid: "))  # Ask the user for base length
            height = int(input("Enter the height of the pyramid: ")) # Ask the user for height
            currentVolume = volumes.pyramidVolume(baseLength, height)  # Calculate the volume by sending both base and
                                                                       # height to method "pyramidVolume" within
                                                                       # 'volumes' module.
            pyramidVolumes.append(currentVolume)  # Add the volume to the pyramids list

        # If the user input was "ellipsoid" or "e", perform necessary actions.
        elif index in range(4,6):

            # Ask for the three radii of the ellipsoid
            radius1 = int(input("Enter the first radius: "))
            radius2 = int(input("Enter the second radius: "))
            radius3 = int(input("Enter the third radius: "))
            currentVolume = volumes.ellipsoidVolume(radius1, radius2, radius3)  # Calculate the volume by sending the
                                                                                # 3 radii to method 'ellipsoidVolume'
                                                                                # within 'volumes' module.
            ellipsoidVolumes.append(currentVolume)   # Add the volume to the ellipsoids list.

        # If the user input was "quit" or "q", change 'isShape' to false to allow program to exit loop
        elif index in range(6,8):

            isShape = False

        valid = False  # Reset 'valid' to false, allowing program to loop through asking user for shape input again
                       # if necessary.

    # Sort the volumes within each shape volume list in ascending order.
    cubeVolumes.sort()
    pyramidVolumes.sort()
    ellipsoidVolumes.sort()

    # Notify that the session has finished.
    print("")
    print("")
    print("You have reached the end of your session.")

    # If the user has not performed any calculations, print appropriate message
    if len(cubeVolumes) == 0 and len(pyramidVolumes) == 0 and len(ellipsoidVolumes) == 0:
        print("You did not perform any volume calculations.")
    else:

        print("The volumes calculated for each shape are:")

        # If there are calculated cube volumes, print them.
        if len(cubeVolumes) != 0:
            print("Cube: ", cubeVolumes)
        else:
            print("Cube: No shapes entered")  # If there are no cube volumes, print appropriate message

        # If there are calculated pyramid volumes, print them.
        if len(pyramidVolumes) != 0:
            print("Pyramid: ", pyramidVolumes)
        else:
            print("Pyramid: No shapes entered")  # If there are no pyramid volumes, print appropriate message

        # If there are calculated ellipsoid volumes, print them.
        if len(ellipsoidVolumes) != 0:
            print("Ellipsoid: ", ellipsoidVolumes)
        else:
            print("Ellipsoid: No shapes entered")  # If there are no ellipsoid volumes, print appropriate message

    # Within the 'summarize' module, send all the lists of volumes and test case number to the "summarize" method.
    # This will print them to a text file with the appropriate test case number.
    summarize.summarize(cubeVolumes, pyramidVolumes, ellipsoidVolumes, testCase)

# Call the main function to run it.
main()
