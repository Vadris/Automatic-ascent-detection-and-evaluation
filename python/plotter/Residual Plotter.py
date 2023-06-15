import csv
import matplotlib.pyplot as plt
from fractions import Fraction
import sys

#Function to read in a CSV file with height profile data
def readin_heightprofile_from_csv(filepath:str):
    """Reads in a CSV file with two pieces of information, ..."""
    Distance = []   # List for distance values
    Elevation = []  # List for elevation values

    with open(filepath, newline='') as csvfile:
        spamreader = csv.reader(csvfile, delimiter=',', quotechar='|')
        for row in spamreader:
            Distance.append(float(row[0]))     # Add distance value to the list
            Elevation.append(float(row[-1].rstrip(row[-1][-1])))  # Add elevation value to the list
            
    return Distance, Elevation

#Function to plot to graphs in one coordinatesystem 
def plot_two_plots (filepath1, filepath2):

    Distance1, Elevation1 = readin_heightprofile_from_csv(filepath1)
    Distance2, Elevation2 = readin_heightprofile_from_csv(filepath2)

    plt.figure()
    plt.plot(Distance1,Elevation1)
    plt.plot(Distance2,Elevation2)

    plt.title ('Tour de France')
    plt.xlabel('Distance in meters')
    plt.ylabel('Elevation in meters')

    plt.legend(["Raw Data","Smoothed Data"], loc="upper right")

    # Display the plot
    plt.show()
   
    print(len(Elevation1),len(Elevation2))

def residual_graph (filepath1, filepath2):
    Distance1, Elevation1 = readin_heightprofile_from_csv(filepath1)
    Distance2, Elevation2 = readin_heightprofile_from_csv(filepath2)

    if ( len(Distance1) != len(Distance2) ) or ( len(Elevation1) != len(Elevation2)): print('WARNING! Graphs are inconsistent.') 
    print(max(Distance1),max(Distance2))

    residuals = []
    for current_point in range (len(Elevation1)):
        print(Elevation2[current_point],Elevation1[current_point])
        residuals.append(Elevation1[current_point]-Elevation2[current_point])

        

    plt.figure()
    plt.plot(Distance2, residuals)

    plt.title ('Tour de France')      #Titel of the graph
    plt.xlabel('Distance in meters')  #Label of the x-axis
    plt.ylabel('Elevation in meters') #Label of the y-axis

    #show a legend
    plt.legend (["Residual Graph"], loc="upper right")

    #Display the plot
    plt.show()


#File path to the CSV file
filepath1 = 'data/csv/raw/raw9.csv'
filepath2 = 'data/csv/smoothingTestData/test2.csv'
filepath3 = 'data/TourDeFrance 2022 raw/raw1.csv'
filepath4 = 'data/smoothed1.csv'

#filepath1 = 'data/'+str(sys.argv[1])
#filepath2 = 'data/'+str(sys.argv[2])


#plot_two_plots(filepath1, filepath2) 
residual_graph (filepath1, filepath2)
