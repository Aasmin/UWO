##
# TA: Aasminpreet Singh Kainth
# This program calculates the cost, including taxes, of breakfast(s) at the "Good Morning America!"
# restaurant based on valid user inputs for items and quantities.

## CONSTANTS ##

# This is the tax percent value.
TAX_PERCENT = 0.13
# The dollar prices per unit of every menu item.
EGG, BACON, SAUSAGE, HASH_BROWN, TOAST, COFFEE, TEA = 0.99, 0.49, 1.49, 1.19, 0.79, 1.09, 0.89

# The main method that asks user for input and calculates breakfast cost.
def main():

    cost= 0  # Initialize pre-tax cost at $0.00

    item = ""  # Initialize menu item input variable as empty string
    quantity = 0  # Initialize quantity input variable at 0

    # Loop to keep asking user to input menu item and quantity until they quit. As long as "q" isn't inputted,
    # this loop will continue to run.
    while item!="q":

        validInput = False # This boolean determines when to exit the loops for menu item and quantity input

        # Loop through asking the user for menu item input. Will run at least once due to initialization above.
        while validInput == False:

            # Ask the user for menu item input
            item = str(input("Enter item (q to terminate): small breakfast, regular breakfast, big breakfast, egg, \nbacon, "
                         "sausage, hash brown, toast, coffee, tea: "))

            # Send input to method that cleaves leading, trailing and multiple spaces between. Also send to lowercase.
            item = formatInput(item)

            # Check to see if the input matches one of the menu items, after formatting.
            if (item != "small breakfast" and item != "regular breakfast" and item != "big breakfast" and item != "egg"
                and item!= "bacon" and item != "sausage" and item != "hash brown" and item != "toast" and item != "coffee"
                and item!= "tea" and item!= "q"):

                # If it doesn't match, send the error message and loop through again.
                print("Error. Please submit a valid menu item.")
                print("")

            else:

                # If it does match, change the variable value to exit the loop.
                validInput = True

        # Check to see that the user input isn't "q" to quit before asking for quantity input.
        if item!="q":

            validInput = False # Reset the variable

            # Loop through asking the user for quantity input. Will run at least once.
            while validInput == False:

                quantity = str(input("Enter quantity: "))   # Ask for quantity input

                # Check to see if input is an integer
                if quantity.isnumeric():
                    validInput = True    # If it is an integer, set value to exit the loop
                    quantity = int(quantity)    # Convert the input into an integer
                else:
                    # if it's not a number, print error message and loop through again
                    print("Error. Please enter an integer quantity value.")
                    print("")

        # This if-elif structure adds the cost of the inputted menu item multiplied by quantity to the total price
        # using the corresponding constant value.
        if item == "egg":
            cost += EGG * quantity
        elif item == "bacon":
            cost += BACON * quantity
        elif item == "sausage":
            cost += SAUSAGE * quantity
        elif item == "hash brown":
            cost += HASH_BROWN * quantity
        elif item == "toast":
            cost += TOAST * quantity
        elif item == "coffee":
            cost += COFFEE * quantity
        elif item == "tea":
            cost += TEA * quantity
        elif item == "small breakfast":
            # Compute small breakfast price using constant values and predetermined quantity values
            cost += quantity * (EGG + HASH_BROWN + (2 * TOAST) + (2 * BACON) + SAUSAGE)
        elif item == "regular breakfast":
            # Compute regular breakfast price using constant values and predetermined quantity values
            cost += quantity * ((2 * EGG) + HASH_BROWN + (2 * TOAST) + (4 * BACON) + (2 * SAUSAGE))
        elif item == "big breakfast":
            # Compute big breakfast price using constant values and predetermined quantity values
            cost += quantity * ((3 * EGG) + (2 * HASH_BROWN) + (4 * TOAST) + (6 * BACON) + (3 * SAUSAGE))

    # Calculate the tax amount using 13% tax rate
    taxCost = cost * TAX_PERCENT

    print("")
    # Display the pre-tax cost, the tax amount, and the final cost to 2 decimal places.
    print("Cost: $%.2f" % (cost))
    print("Tax: $%.2f" % (taxCost))
    print("Total: $%.2f" % (taxCost+cost))

# Method given in the assignment to cleave leading, trailing and multiple spaces in between.
def formatInput(textLine):

    textLine = textLine.lower().strip()
    wordList = textLine.split()
    textLine = " ".join(wordList)
    return textLine

# Call the main method
main()
