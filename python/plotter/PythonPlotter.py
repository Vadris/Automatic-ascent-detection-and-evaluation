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

def show_graph(Distance,Elevation):
    fig = plt.figure()
    ax = fig.add_subplot()
    plt.plot(Distance, Elevation)

    fig.suptitle('Tour de France', fontsize=14, fontweight='bold')
    ax.set_xlabel('Distance')
    ax.set_ylabel('Elevation')

    plt.show()

filepath = 'python/plotter/test-height-data1-fixed.csv'
Distance,Elevation = readin_heightprofile_from_csv(filepath)
show_graph(Distance,Elevation)