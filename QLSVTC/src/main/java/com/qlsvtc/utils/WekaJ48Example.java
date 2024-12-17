package com.qlsvtc.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.qlsvtc.model.WekaModel;
import com.qlsvtc.model.baocao.BCPhieuDiem;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class WekaJ48Example {
	public static List<WekaModel> J48(List<WekaModel> testDataList) {
		try {
			DataSource trainSource = new DataSource("D:/data.csv");
			Instances trainData = trainSource.getDataSet();
			if (trainData.classIndex() == -1) {
				trainData.setClassIndex(trainData.numAttributes() - 1);
			}

			J48 tree = new J48();
			tree.buildClassifier(trainData);
			int index;
			Instances testData = new Instances(trainData, 0); 
			for (WekaModel model : testDataList) {
				double[] values = new double[trainData.numAttributes()];
				index = model.getSoTC(); 
				if (index == -1) {
					continue;  
				}
				values[0] = index;
				index = trainData.attribute(1).indexOfValue(model.getTyLeTH());
				if (index == -1) {
					continue;  
				}
				values[1] = index;
				index = trainData.attribute(2).indexOfValue(model.getKyThuat());
				if (index == -1) {
					continue;  
				}
				values[2] = index;
				index = trainData.attribute(3).indexOfValue(model.getPhanTich());
				if (index == -1) {
					continue;  
				}
				values[3] = index;
				index = trainData.attribute(4).indexOfValue(model.getThietKe());
				if (index == -1) {
					continue;  
				}
				values[4] = index;
				index = trainData.attribute(5).indexOfValue(model.getKyNangNhom());
				if (index == -1) {
					continue;  
				}
				values[5] = index;

				index = trainData.attribute(6).indexOfValue(model.getDiemCC());
				if (index == -1) {
					continue;  
				}
				values[6] = index;
				index = trainData.attribute(7).indexOfValue(model.getDiemTK());
				if (index == -1) {
					continue;  
				}
				values[7] = index;
				//values[8] = trainData.attribute(7).indexOfValue(model.getChuyenNganh());

				Instance instance = new DenseInstance(1.0, values);
				testData.add(instance);
				System.out.println(instance);
			}
			testData.setClassIndex(trainData.classIndex()); 

			for (int i = 0; i < testData.numInstances(); i++) {
				double label = tree.classifyInstance(testData.instance(i));
				System.out.println(
						"Instance " + i + ": predicted class = " + testData.classAttribute().value((int) label));
				testDataList.get(i).setChuyenNganh(testData.classAttribute().value((int) label));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return testDataList;
	}

	public static List<WekaModel> readMH() {
		String csvFile = "D:/mh.csv";
		List<WekaModel> items = new ArrayList<>();

		try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
			String[] nextLine;
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				WekaModel item = new WekaModel();
				item.setMaMH(nextLine[0]);
				item.setTenMH(nextLine[1]);

				item.setSoTC(Integer.parseInt(nextLine[2]));
				item.setTyLeTH(nextLine[3]);
				item.setKyThuat(nextLine[4]);

				item.setPhanTich(nextLine[5]);

				item.setThietKe(nextLine[6]);

				item.setKyNangNhom(nextLine[7]);

				items.add(item);
			}
		} catch (IOException | CsvException e) {
			e.printStackTrace();
		}

		for (WekaModel item : items) {
			System.out.println(item);
		}
		return items;
	}
	
	private static int timChiSo(List<WekaModel> danhSach, String maMH) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaMH().equals(maMH)) {
                return i; // Tr? v? ch? s? n?u tìm th?y
            }
        }
        return -1; // Tr? v? -1 n?u không tìm th?y
    }
	
	public static List<WekaModel> capNhatWekaLst(List<WekaModel> danhSach, List<BCPhieuDiem> lstPD) {
		String maMH;
        for (int i = 0; i < lstPD.size(); i++) {
        	maMH=lstPD.get(i).getMaMH();
        	int j=timChiSo(danhSach,maMH);
            if (j!=-1) {
            	danhSach.get(j).setDiemCC(lstPD.get(i).getDiemCC2());
            	danhSach.get(j).setDiemTK(lstPD.get(i).getDiemTK2());
    			System.out.println("capnhat: "+danhSach.get(j));

            }
        }
        return danhSach; // Tr? v? -1 n?u không tìm th?y
    }
}
