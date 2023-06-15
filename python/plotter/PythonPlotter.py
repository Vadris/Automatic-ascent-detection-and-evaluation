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

#filepath1 = 'data/csv/raw/raw9.csv'
#filepath1 = 'data/csv/smoothingTestData/test2a.csv'
#filepath2 = 'data/csv/smoothingTestData/test2.csv'
filepath3 = 'data/csv/smoothed/smoothed5.csv'

filepath1 = 'data/csv/raw/raw3.csv'
filepath2 = 'data/csv/smoothed/smoothed-v3-3.csv'

Distance1,Elevation1 = readin_heightprofile_from_csv(filepath1)
Distance2,Elevation2 = readin_heightprofile_from_csv(filepath2)
Distance3, Elevation3 = readin_heightprofile_from_csv(filepath3)

fig = plt.figure()
ax = fig.add_subplot()
plt.plot(Distance1, Elevation1, 'red')
plt.plot(Distance2, Elevation2, 'green')
#plt.plot(Distance3, Elevation3, 'blue')

fig.suptitle('Tour de France', fontsize=14, fontweight='bold')
ax.set_xlabel('Distance')
ax.set_ylabel('Elevation')
plt.show()

