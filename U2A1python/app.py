import tkinter as toolkit
root = toolkit.Tk()

root.title("Employee Records App Python")
root.geometry("600x600")

header = toolkit.Label(root, text="Employee Records App!", font="Arial 30 bold")
header.place(x=30, y=40)

def createLabelAndEntry(labelText, x, y):
    label = toolkit.Label(root, text=labelText)
    label.place(x=x, y=y)

    entry = toolkit.Entry(root, width=20)
    entry.place(x=x+90, y=y-2)
    return entry

identification = createLabelAndEntry("ID #", 30, 100)
firstName = createLabelAndEntry("First Name:", 30, 130)
lastName = createLabelAndEntry("Last Name:", 30, 160)
salary = createLabelAndEntry("Annual Salary:", 30, 190)
startDate = createLabelAndEntry("Start Date:", 30, 220)

output = toolkit.Text(root, width=20, height=3)
output.place(x=30, y=300)

root.mainloop()