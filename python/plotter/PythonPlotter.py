import matplotlib.pyplot as plt
import csv

def readin_heightprofile_from_csv(filepath:str):
    """ Reads in a csv with two pieces of information, ..."""
    Distance=[]
    Elevation=[]

    with open(filepath, newline='') as csvfile:
        spamreader = csv.reader(csvfile, delimiter=',', quotechar='|')
        for row in spamreader:
            Distance.append(float(row[0]))
            Elevation.append(float(row[-1].rstrip(row[-1][-1])))
            
    return Distance, Elevation

def show_graph(Distance,Elevation,graphcolor='blue'):
    fig = plt.figure()
    ax = fig.add_subplot()
    plt.plot(Distance, Elevation, color=graphcolor)

    fig.suptitle('Tour de France', fontsize=14, fontweight='bold')
    ax.set_xlabel('Distance')
    ax.set_ylabel('Elevation')

filepath1 = 'python/plotter/smoothedData1.csv'
filepath2 = 'python/plotter/test-height-data1-fixed.csv'
Distance1,Elevation1 = readin_heightprofile_from_csv(filepath1)
Distance2,Elevation2 = readin_heightprofile_from_csv(filepath2)
show_graph(Distance1,Elevation1)
show_graph(Distance2, Elevation2, 'red')
plt.show()

