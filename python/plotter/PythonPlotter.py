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

filepath1 = 'data/csv/raw/raw4.csv'
filepath2 = 'data/csv/smoothed/smoothed4.csv'
filepath3 = 'data/csv/polynomial/testPolynomial1.csv'
Distance1,Elevation1 = readin_heightprofile_from_csv(filepath1)
Distance2,Elevation2 = readin_heightprofile_from_csv(filepath2)
Distance3, Elevation3 = readin_heightprofile_from_csv(filepath3)

fig = plt.figure()
ax = fig.add_subplot()
plt.plot(Distance1, Elevation1, 'red')
plt.plot(Distance2, Elevation2, 'blue')
#plt.plot(Distance3, Elevation3, 'green')

fig.suptitle('Tour de France', fontsize=14, fontweight='bold')
ax.set_xlabel('Distance')
ax.set_ylabel('Elevation')
plt.show()

