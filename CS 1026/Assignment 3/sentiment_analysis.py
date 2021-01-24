##
# TA: Aasminpreet Singh Kainth
# This sentiment_analysis module contains the functions necessary for reading tweets and determining
# the happiness values of them. It also determines time-zones and calculates averages.

# Import all punctuation symbols from "string" module
from string import punctuation

#
# This function processes the file of tweets and keywords, calculating their happiness values.
# @param tweetName is the file of tweets.
# @param keywordName is the file of keywords.
# @return The function will return a list of 4 tuples for each of the time-zones that contain the average
# happiness value, the number of keyword tweets and the total number of tweets
def compute_tweets(tweetName, keywordName):

    # Initialize the keyword dictionary, a list of lists and the time-zone value
    keywordsDictionary = {}
    scoreData = [[0,0,0], [0,0,0], [0,0,0], [0,0,0]]
    timeZone = -1

    # Try-Except block to see if files can be opened
    try:

        # Open the file of tweets and keywords in read-mode
        tweetFile = open(tweetName,"r",encoding="utf-8")
        keywordFile = open(keywordName, "r", encoding="utf-8")

        # Go through every line in the keyword file
        for line in keywordFile:
            # Modify the line in the file by stripping spaces and separating by commas
            keywordInput = lineModifier(line, ",", " ")
            # Set the key in the keywords dictionary as the keyword and the value as the happiness value
            keywordsDictionary[keywordInput[0]] = int(keywordInput[1])


        # Go through every line in the tweet file
        for line in tweetFile:
            # Modify the tweet line by stripping the leading/trailing punctuation and separating based on spaces
            tweetInput = lineModifier(line," ",punctuation)

            happinessScore = 0 # This is averaged happiness score of the tweet
            sentimentValue = 0 # This is the accumulated sentiment value of the tweet
            numKeywords = 0 # This is the number of keywords in the tweet

            # Loop through every word in the tweet
            for i in range(len(tweetInput)):

                # If the tweet matches one of the keywords, add the sentiment value to the total sentiment value
                # and add 1 to the number of keywords in the tweet.
                if tweetInput[i] in keywordsDictionary:
                    sentimentValue = sentimentValue + keywordsDictionary[tweetInput[i]]
                    numKeywords += 1

            # If there actually are keywords, calculate the average happiness value by dividing the total
            # sentiment value by the number of keywords.
            if numKeywords > 0:
                happinessScore = sentimentValue/numKeywords

            # Convert the latitude and longitude of the tweet into floating point numbers
            tweetInput[0] = float(tweetInput[0])
            tweetInput[1] = float(tweetInput[1]) * -1

            # Determine the time-zone of the tweet by sending latitude and longitude to function
            timeZone = zoneFinder(tweetInput[0], tweetInput[1])

            # If the time-zone was found, run IF-Statment
            if timeZone != -1:

                # Add the average happiness value of the tweet to the corresponding time-zone list
                scoreData[timeZone][0] = scoreData[timeZone][0] + happinessScore

                # If the happiness score is greater than 0, indicating that the tweet contained keywords, add 1
                # to the number of keyword tweets in that time-zone.
                if happinessScore > 0:
                    scoreData[timeZone][1] += 1

                # Add 1 to total number of tweets of that time-zone
                scoreData[timeZone][2] += 1

        # Go through every time-zone's accumulated happiness scores
        for x in range(0,4):

            # If the accumulated happiness scores are more than 0, calculate the time-zone's average happiness value
            # by dividing the accumulated happiness score by the number of keyword tweets.
            if scoreData[x][1]>0:
                scoreData[x][0] = scoreData[x][0]/scoreData[x][1]

            # Convert the list of lists into a list of tuples
            scoreData[x] = tuple(scoreData[x])

        # Close both files
        tweetFile.close()
        keywordFile.close()

        # Return the list of tuples
        return scoreData

    # If files cannot open, catch the IO Error
    except IOError:

        print("Could not find text files.")
        return []

#
# This function determines the time-zone given a latitude and longitude
# @param lat is latitude
# @param long is longitude
# @return This function will return a value that corresponds to a time-zone
def zoneFinder(lat, long):

    # Ensure the latitude is within bounds
    if lat>24.660845 and lat<49.189787:
        # Eastern time-zone
        if long<-67.444574 and long>-87.518395:
            return 0
        # Central time-zone
        elif long<-87.518395 and long>-101.998892:
            return 1
        # Mountain time-zone
        elif long<-101.998892 and long>-115.236428:
            return 2
        # Pacific time-zone
        elif long<-115.236428 and long>-125.242264:
            return 3

    # If the latitude is not within bounds, return a -1 to indicate the time-zone could not be found
    return -1

#
# This function will modify a string through Python built-in functions.
# @param sentence is the string to be modified.
# @param divider is the character to split the string by.
# @param stripElement is the character to remove from the beginning and the end.
# @return This function will return the modified list of strings.
def lineModifier(sentence, divider, stripElement):

    # Split the string into a list of strings based on the "divider" character
    words = sentence.split(divider)

    # Loop through the list of strings
    for i in range(len(words)):
        words[i] = words[i].lower() # Set each word to lowercase
        words[i] = words[i].strip() # Remove the spaces before and after on the word

        # If the character to strip wasn't a space, then continue and strip the character from the word
        if stripElement != " ":
            words[i] = words[i].strip(stripElement)

    return words  # return the list of strings
