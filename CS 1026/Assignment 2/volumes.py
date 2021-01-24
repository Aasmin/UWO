##
# TA: Aasminpreet Singh Kainth
# This module has three different methods that calculates the volumes and returns them for each corresponding shape.
#

# import the pi value from math module
from math import pi

# Cube volume calculation method, takes in one side length as a parameter
def cubeVolume(side):

    volume = side**3  # Set the volume as the side length raised to the 3rd power.

    return volume # return the calculated volume

# Pyramid volume calculation method, takes in the base and height as parameters
def pyramidVolume(base, height):

    volume = (1/3)*(base**2)*height  # Set the volume as the base squared, multiplied by a third of the height

    return volume # return the calculated volume

# Ellipsoid volume calculation method, takes in three radii as the parameters
def ellipsoidVolume(radius1, radius2, radius3):

    volume = (4/3) * pi * radius1 * radius2 * radius3  # Set the volume as the product of the three radii, pi and 4/3

    return volume  # return the calculated volume
