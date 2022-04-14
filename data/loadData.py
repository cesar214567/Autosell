import psycopg2
import pandas as pd
import csv

def loadTable(connection, fileName, tableName,sep=';'):
    with open(fileName, 'r') as file:
        cursor.copy_from(file, tableName, sep,null='')
    connection.commit()
    print("Table " + tableName + " inserted successfully")


def printSep(text):
    print("-------------------------------------------------------------------")
    print("-------------------------------------------------------------------")
    print("                           " + text)
    print("-------------------------------------------------------------------")
    print("-------------------------------------------------------------------")


try:
    #connection = psycopg2.connect(user="doadmin",
    #
    #                                 password="23EcLS18TNFIqygD",
    #                             host="db-postgresql-nyc1-93403-do-user-10253325-0.b.db.ondigitalocean.com",
    #                             port="25060",
    #                             database="defaultdb")

    connection = psycopg2.connect(user="postgres",
                                   password="admin",
                                    host="127.0.0.1",
                                     port="5432",
                                     database="postgres")
    cursor = connection.cursor()
    #printSep('carros')
    loadTable(connection, 'autoseminuevo2.csv', 'auto_semi_nuevo',',')
    #printSep('relaciones')
    #loadTable(connection, 'relaciones.csv', 'usuario_carros_posteados')
    printSep('accesorio')
    loadTable(connection, 'accesorios.csv', 'accesorio')


    printSep('FINISHED')

except (Exception, psycopg2.Error) as error:
    if connection:
        print("Failed to insert record", error)

finally:
    if connection:
        cursor.close()
        connection.close()
        print("PostgreSQL connection is closed")