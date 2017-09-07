
import java.io.FileOutputStream;
import java.util.*;
import javax.swing.JOptionPane;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lavonidos
 */
public class currentUser {

    private String mUserName, mRamType, rRamType, mSocket, cSocket, mFormFactor, cFormFactor, mHanging;
    private Map<String, String> mComponents;
    private Map<String, Float> mPrices;
    private float mPrice, mBudget;
    private int mTDP, mMaxRam, cMaxRam, mSLI, mCrossfire, mVCL, cVCL, mSlots, rSlots, cRam;
    private boolean checkBudget;

    public currentUser(String name) {
        mUserName = name;
        mComponents = new HashMap<String, String>();
        mPrices = new HashMap<String, Float>();
        mPrice = 0;
        mTDP = 0;
        mMaxRam = 0;
        cMaxRam = 0;
        mSocket = null;
        mRamType = null;
        mFormFactor = null;
        cFormFactor=null;
        mSLI = 0;
        mCrossfire = 0;
        mVCL = 0;
        cRam = 0;
        mSlots = 0;
        mBudget = 0;
        checkBudget = false;

    }

    public String getUserName() {
        return mUserName;
    }

    public void addPrice(float price) {
        mPrice += price;
    }

    public void setMotherBoard(String name, String socket, String formFactor, String ramType, int slots, int maxRam, int sli, int crossfire, float price) {
        mFormFactor=formFactor;
        if (mComponents.containsKey("Motherboard") == false) {
            if (mComponents.containsKey("CPU") && !socket.equalsIgnoreCase(cSocket)) {
                JOptionPane.showMessageDialog(null, "Motherboard is not compatible with selected CPU!!");
                return;
            }
            if (mComponents.containsKey("RAM") && !ramType.equalsIgnoreCase(rRamType)) {
                JOptionPane.showMessageDialog(null, "Motherboard is not compatible with selected RAM!!");
                return;
            }
            if (mComponents.containsKey("Case") && caseComp()) {
                JOptionPane.showMessageDialog(null, "Motherboard is not compatible with selected Case!!");
                return;
            }
            if (mComponents.containsKey("RAM") && (rSlots > slots)) {
                JOptionPane.showMessageDialog(null, "Motherboard has less slots than the modules of selected RAM!!");
                return;
            }
            if (mComponents.containsKey("RAM") && (cRam > maxRam)) {
                JOptionPane.showMessageDialog(null, "Motherboard do not support the size of selected RAM!!");
                return;
            } else {

                mComponents.put("Motherboard", name);
                mPrices.put(name, price);
                mRamType = ramType;
                mSocket = socket;
                mMaxRam = maxRam;
                
                mSLI += sli;
                mCrossfire += crossfire;
                mSlots = slots;
                JOptionPane.showMessageDialog(null, name+" added!!");
            }

        } else {
            JOptionPane.showMessageDialog(null, "A motherboard already exists!!");
        }
    }

    public int getMaxRam() {
        return mMaxRam;
    }

    public void setCPU(String name, String socket, int maxRam, int tdp, float price) {
        cMaxRam=maxRam;
        if (!mComponents.containsKey("CPU")) {
            if (mComponents.containsKey("Motherboard") && !socket.equalsIgnoreCase(mSocket)) {
                JOptionPane.showMessageDialog(null, "Motherboard is not compatible with selected CPU!!");
                return;
            }
            if (mComponents.containsKey("RAM") && (cRam > maxRam)) {
                JOptionPane.showMessageDialog(null, "CPU do not support the size of selected RAM!!");
                return;
            } else {
                mComponents.put("CPU", name);
                mTDP += tdp;
                mPrices.put(name, price);
                cSocket = socket;
                JOptionPane.showMessageDialog(null, name+" added successfully");
            }

        } else {
            JOptionPane.showMessageDialog(null, "A CPU already exists!!");
        }

    }

    public void setCPUCooler(String name, float price) {
        if (!mComponents.containsKey("CPU Cooler")) {

            mComponents.put("CPU Cooler", name);
            mPrices.put(name, price);
            JOptionPane.showMessageDialog(null, "CPU Cooler added successfully");

        } else {
            JOptionPane.showMessageDialog(null, "A CPU cooler is already present!!");
        }
    }

    public void setRAM(String name, String ramType, int modules, int size, float price) {
        int maxram = 0;

        if (!mComponents.containsKey("RAM")) {
            if (mComponents.containsKey("Motherboard") && (size > mMaxRam)) {
                JOptionPane.showMessageDialog(null, "Motherboard do not support the size of selected RAM!!");
                return;
            }
            if (mComponents.containsKey("CPU") && (size > cMaxRam)) {
                JOptionPane.showMessageDialog(null, "CPU do not support the size of selected RAM!!");
                return;
            }
            if (mComponents.containsKey("Motherboard") && !ramType.equalsIgnoreCase(mRamType)) {
                JOptionPane.showMessageDialog(null, "Motherboard is not compatible with selected RAM!!");
                return;
            }
            if (mComponents.containsKey("Motherboard") && (modules > mSlots)) {
                JOptionPane.showMessageDialog(null, "Motherboard has less slots than the modules of selected RAM!!");
                return;
            } else {

                mComponents.put("RAM", name);
                cRam = size;
                mPrices.put(name, price);
                rRamType = ramType;
                rSlots = modules;
                JOptionPane.showMessageDialog(null, "RAM added!!");
            }

        } else {
            JOptionPane.showMessageDialog(null, "RAM already present!!");
        }

    }

    public void setGraphicsCard(String name, int tdp, int length, float price) {

        if (!mComponents.containsKey("Graphics Card")) {
            if (mComponents.containsKey("Case") && (length > cVCL)) {
                JOptionPane.showMessageDialog(null, "Selected Graphic Card cannot fit in your Case!!");

                return;
            } else {
                mComponents.put("Graphics Card", name);
                mTDP += tdp;
                mVCL = length;
                mPrices.put(name, price);
                JOptionPane.showMessageDialog(null, name+" added successfully!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A Graphic card is already present and if your Motherboard can support multiple Graphics Cards, then you can add them on next page.");
        }
    }

    public void setCase(String name, String formFactor, int vcl, float price) {

        cFormFactor = formFactor;
        if (!mComponents.containsKey("Case")) {
            if (mComponents.containsKey("Graphics Card") && (mVCL > vcl)) {
                JOptionPane.showMessageDialog(null, "Your Graphic Card cannot fit in this Case!!");

                return;
            }
            if (mComponents.containsKey("Motherboard") && caseComp()) {
                JOptionPane.showMessageDialog(null, "Your Motherboard is not compatible with selected Case!!");
                return;
            } else {

                mComponents.put("Case", name);

                cVCL = vcl;
                mPrices.put(name, price);
                JOptionPane.showMessageDialog(null, name+" added successfully!!");

            }

        } else {
            JOptionPane.showMessageDialog(null, "A case is already present!!");
        }

    }

    public boolean caseComp() {
        if (cFormFactor.equalsIgnoreCase("ATX")) {
            return true;
        }
        else if (cFormFactor.equalsIgnoreCase("Micro ATX") && (mFormFactor.equalsIgnoreCase("Micro ATX") || mFormFactor.equalsIgnoreCase("Mini ATX") || mFormFactor.equalsIgnoreCase("Mini ITX") || mFormFactor.equalsIgnoreCase("Micro ITX"))) {
            return true;
        }
        else if (cFormFactor.equalsIgnoreCase("Mini ATX") && (mFormFactor.equalsIgnoreCase("Mini ATX") || mFormFactor.equalsIgnoreCase("Mini ITX"))) {
            return true;
        } else {
            return false;
        }
    }

    public void setStorage(String name, float price) {
        if (!mComponents.containsKey("Storage")) {
            mComponents.put("Storage", name);
            mPrices.put(name, price);
            JOptionPane.showMessageDialog(null, "" + name + " added successfully!!");
        } else {
            JOptionPane.showMessageDialog(null, "You already added a storage");
        }
    }

    public void setPSU(String name, float price) {
        if (!mComponents.containsKey("PSU")) {
            mComponents.put("PSU", name);
            mPrices.put(name, price);
            JOptionPane.showMessageDialog(null, name + " successfully added!!");
        } else {
            JOptionPane.showMessageDialog(null, "You already added a PSU");
        }
    }

    public List getComponents() {
        List list = new ArrayList<String>(mComponents.values());

        return list;
    }

    public int getTDP() {
        return mTDP;
    }

    public void removeComponent(String name) {
        
            for (Map.Entry<String, String> entry : mComponents.entrySet()) {
                if (name.equalsIgnoreCase(entry.getValue())) {
                    mComponents.remove(entry.getKey());
                    mPrices.remove(entry.getValue());
                }
            }
        
    }

    public void removePart(String key) {
        if (mComponents.containsKey(key)) {
            String name = mComponents.get(key);
            removeComponent(name);
        }
    }

    public void removeAll() {
        mComponents.clear();
        mPrices.clear();
    }

    public Map<String, String> getMap() {
        return mComponents;
    }

    public Map<String, Float> getPriceMap() {
        return mPrices;
    }

    public float getPrice() {
        mPrice = 0;
        for (Map.Entry<String, Float> entry : mPrices.entrySet()) {

            mPrice += entry.getValue();

        }
        return mPrice;

    }

    public void savePDF() {
        Document document = new Document();
        String product = null, name = null;
        float price;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(mUserName + getRandom() + ".pdf"));
            document.open();
            document.add(new Paragraph("PC assembled by " + mUserName + " on " + dateFormat.format(date) + " :\n\n"));

            for (Map.Entry<String, String> entry : mComponents.entrySet()) {
                product = entry.getKey();
                name = entry.getValue();
                price = mPrices.get(entry.getValue());
                document.add(new Paragraph("\n" + product + " : " + name + " : Rs." + price));
            }
            document.add(new Paragraph("\n\nTotal Cost: Rs." + getPrice()));
            document.add(new Paragraph("\n Thank you for using Virtual PC Designer."));
            document.close();
            writer.close();
            JOptionPane.showMessageDialog(null, "PDF file saved!!");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getRandom() {
        Random r = new Random();
        int n = r.nextInt();
        if (n < 0) {
            n = n * (-1);
        }
        return n;
    }

    public void setBudget(float b) {
        mBudget = b;
        checkBudget = true;
    }

    public float getBudget() {
        return mBudget;
    }

    public float getMoney() {
        float m = mBudget - getPrice();

        return m;
    }

    public boolean isExceeded(String name) {
        if (checkBudget) {
            float m = mBudget - getPrice();
            if (m < 0) {
                mHanging = name;
                return true;
            }
            return false;
        }
        return false;
    }

    public float getExceeded() {
        float m = getPrice() - mBudget;
        return m;
    }

    public void setCheck(boolean b) {
        checkBudget = b;
    }

    public String gethName() {
        return mHanging;
    }

    public boolean toCheck() {
        return checkBudget;
    }
}
