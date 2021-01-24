##
# TA: Aasminpreet Singh Kainth
# This main program calculates the average happiness score, the number of keyword tweets and
# the number of total tweets of different time zones by calling the "compute_tweets" function.

# Import the function "compute_tweets" from the sentiment_analysis module
from sentiment_analysis import compute_tweets

# Define the main method
def main():

    # Introductory statement
    print("Welcome to the Sentiment Calculator.")
    print("")

    # Prompt the user to input the name of the file of tweets and the file of keywords
    tweets = input("Please enter the name of the file with the Tweets: ")
    keywords = input("Please enter the name of the file with the Keywords: ")

    # Send the two files to function "compute_tweets" and set the return list value as scoreData
    scoreData = compute_tweets(tweets, keywords)

    print("")

    # Only perform actions in if-statement if scoreData is not an empty list
    if len(scoreData) != 0:

        # Print out the results in 4 sections based on time-zone
        for i in range(0,4):
            if i == 0:
                print("EASTERN REGION")
            elif i == 1:
                print("CENTRAL REGION")
            elif i == 2:
                print("MOUNTAIN REGION")
            else:
                print("PACIFIC REGION")

            # Print the associated average happiness score, number of keyword tweets and number of tweets
            # of each time-zone. This is stored in scoreData.
            print("")
            print("Average Happiness Score: ",scoreData[i][0])
            print("Number of Keyword Tweets: ",scoreData[i][1])
            print("Number of Tweets: ",scoreData[i][2])
            print("")
            print("")


# Call the main method
main()
