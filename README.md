# VPCD(Virtual PC Designer)

Virtual PC Designer is a Java based application (Netbeans Project) to help the PC Assemblers and enthusiasts to assemble
the PC virtually to check whether the components are compatible with each other or not before buying them and few other 
features like generating a pdf of the final assembled PC with price in rupees and feature to set a budget before choosing components and 
some links to the resources on 'How to assemble a PC' and where to shop for components.

When the program is executed, the .db files will be created in the home directory without any tables
and it will generate error.

I recommend NetBeans to open the project as to avoid any minor compatiblity issues.
Here is a link to download NetBeans:
    https://netbeans.org/downloads/

The compatiblity check logic is all in currentUser.java while JDBC and 
Swing components are implemented in entryForm.java

The embedded SQL client used is h2 (jar is present in jarFiles folder) : http://www.h2database.com/html/main.html 

The custom jar imported to generate PDF in the end of the application is itextPDF (jar is present in jarFiles folder)

Copy the files from the dbFiles to your home directory as it has some entries of PC components and
a user table to store user information.

You are free to play around with the application and database to add new components or just modify the current ones.
Just have fun !
