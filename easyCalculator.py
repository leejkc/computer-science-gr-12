# grid placement demo
import tkinter as toolkit

def button_click(number):
    current = entry.get()
    entry.delete(0, toolkit.END)
    entry.insert(0, current + str(number))

def clear():
    entry.delete(0, toolkit.END)

def calculate():
    try:
        result = eval(entry.get())
        entry.delete(0, toolkit.END)
        entry.insert(0, str(result))
    except Exception:
        entry.delete(0, toolkit.END)
        entry.insert(0, "Error!")

root = toolkit.Tk()
root.title("Calculator!")

entry = toolkit.Entry(root, width=20, font=("Arial 14"))
entry.grid(row=0, column=0, columnspan=4)

buttons = [
    ('7', 1, 0), ('8', 1, 1), ('9', 1, 2), ('/', 1, 3),
    ('4', 2, 0), ('5', 2, 1), ('6', 2, 2), ('*', 2, 3),
    ('1', 3, 0), ('2', 3, 1), ('3', 3, 2), ('-', 3, 3),
    ('0', 4, 0), ('C', 4, 1), ('=', 4, 2), ('+', 4, 3),
]

for (text, row, col) in buttons:
    if text == "=":
        button = toolkit.Button(root, text=text, command=calculate)
    elif text == "C":
        button = toolkit.Button(root, text=text, command=clear)
    else:
        button = toolkit.Button(root, text=text, command=lambda t=text: button_click(t))
    
    button.grid(row=row, column=col, sticky="nsew", ipadx=20, ipady=20)

for i in range(5):
    root.grid_rowconfigure(i, weight=1)
    root.grid_columnconfigure(i, weight=1)

root.mainloop()