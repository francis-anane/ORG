package com.example.franciscustomersdata;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Utils {
    private static ArrayList<Customer> allCustomers;
    public static String customerImagePath;
    public static Customer theCustomer;
    private static ArrayList<Customer> townCustomers;
    private static ArrayList<Town> towns;

    public static void initTowns() {
        BufferedReader br;
        if (towns == null) {
            towns = new ArrayList<>();
        }
        try {
            br = new BufferedReader(new FileReader(new File("/data/data/com.example.franciscustomersdata/files/Towns.txt")));
            ArrayList<String> townNames = new ArrayList<>();
            ArrayList<String> tmpTownNames = new ArrayList<>();
            while (true) {
                String readLine = br.readLine();
                String line = readLine;
                if (readLine == null) {
                    break;
                }
                townNames.add(line);
            }
            Collections.sort(townNames);
            Iterator<String> it = townNames.iterator();
            while (it.hasNext()) {
                String n = it.next();
                if (!tmpTownNames.contains(n)) {
                    tmpTownNames.add(n);
                }
            }
            Iterator<String> it2 = tmpTownNames.iterator();
            while (it2.hasNext()) {
                towns.add(new Town(it2.next()));
            }
            br.close();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    public static ArrayList<Town> getTowns() {
        return towns;
    }

    public static void initAllCustomers(Context context) {
        BufferedReader br;
        customerImagePath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + "/";
        if (allCustomers == null) {
            allCustomers = new ArrayList<>();
        }
        try {
            br = new BufferedReader(new FileReader(new File("/data/data/com.example.franciscustomersdata/files/Customers.txt")));
            while (true) {
                String readLine = br.readLine();
                String line = readLine;
                if (readLine != null) {
                    allCustomers.add(new Customer(line, customerImagePath + line + ".jpg"));
                } else {
                    br.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    public static ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public static void initTownCustomers(String town, Context context) {
        BufferedReader br;
        customerImagePath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + "/";
        String currentTown = town;
        if (townCustomers == null) {
            townCustomers = new ArrayList<>();
        }
        try {
            br = new BufferedReader(new FileReader(new File("/data/data/com.example.franciscustomersdata/files/" + currentTown + ".txt")));
            while (true) {
                String readLine = br.readLine();
                String line = readLine;
                if (readLine != null) {
                    townCustomers.add(new Customer(line, customerImagePath + line + ".jpg"));
                } else {
                    br.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    public static ArrayList<Customer> getTownCustomers() {
        return townCustomers;
    }

    public static void initCustomerDetails(String details, Context context) {
        Throwable th;
        customerImagePath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + "/";
        String currentDetail = details;
        theCustomer = new Customer();
        File file = new File("/data/data/com.example.franciscustomersdata/files/" + currentDetail + ".txt");
        try {
            Customer customer = theCustomer;
            customer.setImageData(customerImagePath + currentDetail + ".jpg");
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object obj = "";
            Object obj2 = "";
            try {
                new Integer(0);
                Integer paid = new Integer(0);
                Integer price = new Integer(0);
                Integer paid2 = paid;
                while (true) {
                    String readLine = br.readLine();
                    String line = readLine;
                    if (readLine != null) {
                        if (line.contains("Name:")) {
                            theCustomer.setName(line);
                        } else if (line.contains("Town:")) {
                            theCustomer.setTown(line);
                        } else if (line.contains("Contact:")) {
                            theCustomer.setContact(line);
                        } else if (line.contains("Address:")) {
                            theCustomer.setAddress(line);
                        } else if (line.contains("Other Names:")) {
                            theCustomer.setOtherNames(line);
                        } else if (line.contains("Partner:")) {
                            theCustomer.setPartner(line);
                        } else if (line.contains("Mother:")) {
                            theCustomer.setMother(line);
                        } else if (line.contains("Father:")) {
                            theCustomer.setFather(line);
                        } else if (line.contains("Item Value:")) {
                            theCustomer.setItemValue(line);
                        } else if (line.contains("Sold Price: ")) {
                            theCustomer.setSalePrice(line);
                            price = new Integer(line.substring(12));
                        } else if (!line.contains("Paid: ") || line.substring(6).isEmpty()) {
                            Log.d("Utils", line);
                        } else {
                            paid2 = new Integer(line.substring(6));
                        }
                        if (paid2.intValue() != 0) {
                            Integer updateBalance = new Integer(price.intValue() - paid2.intValue());
                            Customer customer2 = theCustomer;
                            customer2.setRmBalance("Balance: " + updateBalance);
                            Integer updatePay = new Integer(price.intValue() - updateBalance.intValue());
                            Customer customer3 = theCustomer;
                            customer3.setPaid("Paid: " + updatePay);
                            Log.d("Utils", updatePay.toString());
                        } else if (paid2.intValue() == 0) {
                            Customer customer4 = theCustomer;
                            customer4.setRmBalance("Balance: " + price);
                            theCustomer.setPaid("Paid: 0");
                        }
                    } else {
                        br.close();
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th2) {
                th = th2;
                br.close();
                throw th;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (Throwable th3) {

        }
    }

    public static void replaceSelected(String replaceWith, String type) {
        try {
            BufferedReader file = new BufferedReader(new FileReader("notes.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            while (true) {
                String readLine = file.readLine();
                String line = readLine;
                if (readLine == null) {
                    break;
                }
                inputBuffer.append(line);
                inputBuffer.append(10);
            }
            file.close();
            String inputStr = inputBuffer.toString();
            System.out.println(inputStr);
            if (type.equals("0")) {
                inputStr = inputStr.replace(replaceWith + "1", replaceWith + "0");
            } else if (type.equals("1")) {
                inputStr = inputStr.replace(replaceWith + "0", replaceWith + "1");
            }
            System.out.println("----------------------------------\n" + inputStr);
            FileOutputStream fileOut = new FileOutputStream("notes.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    public static void emptyList() {
        towns = null;
        allCustomers = null;
        townCustomers = null;
    }
}
