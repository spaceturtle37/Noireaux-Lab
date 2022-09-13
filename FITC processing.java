setBatchMode(true);
run("Close All");
close("B&C");

root = "C:/Users/Jcv5ff/Desktop/";
path_in = root + "Pool/";
path_out = root + "Well/";
path_grey = path_out + "FITC_grey/"
path_green = path_out + "FITC_green/";

// look for output folder, if it doesnt exist make it
if (File.exists(path_grey)==0) {
	File.makeDirectory(path_grey);
};
// look for output folder, if it doesnt exist make it
if (File.exists(path_green)==0) {
	File.makeDirectory(path_green);
};


dup1 = "_1";
channel = "FITC";
filter = "";
//filter = channel;

// DO THE FITC PART FIRST


// LOOP UNTIL PROMPTED TO EXIT

retry = true;
counter = 0;
while (retry){

	// OPEN AND DUPLICATE
	
	run("Image Sequence...", "dir=" + path_in + " filter=" + filter + " sort use ");
	name = getTitle();
	run("Duplicate...", "title=[" + name + dup1 + "] duplicate");
	selectWindow(name);
	close();
	selectWindow(name+dup1);


	// RUN IDEAL HISTOGRAM
	setBatchMode(false);
	Stack.getStatistics(voxelCount, mean, hist_min, hist_max, stdDev);
	binning = hist_max - hist_min;
	run("Histogram", "bins="+binning+" x_min ="+hist_min+" x_max="+hist_max+" stack");
	waitForUser("Readjust contrast");
	close("Histogram of "+name+dup1);
	setBatchMode(true);
	selectWindow(name+dup1);

	// SET DISPLAY RANGE AT THE FIRST ITERATION, UPDATE IT AT THE OTHERS

	if (counter == 0){
		setMinAndMax(hist_min, hist_max);
		getMinAndMax(manual_min,manual_max);
	}
	else{
		setMinAndMax(manual_min, manual_max);
	};
	
	// MAKE BRIGHTNESS CHANGES AND SAVE THE GRAY SCALE PICTURES
	//selectWindow(name+dup1);
	//run("Brightness/Contrast...");
	run("Apply LUT", "stack");
	run("Image Sequence... ", "dir=" + path_grey  + " format=TIFF use" );
	
	// STORE THE NAMES OF INDEPENDENT SLICES
	//selectWindow(name+dup1);
	run("Stack to Images");
	ImagesEnhancedTitlesArray = getList("image.titles");
	run("Images to Stack", "name=[" + name + dup1 + "] title=[] use");

	// APPLY GREEN FILTER TO PICTURES
	run("Make Composite");
	run("Merge Channels...", "c2=" + name + dup1 + " title="+name);
	selectWindow("RGB");

	// EXPORT THE GREEN PICTURES WITH MATCHING NAMES FROM GREYSCALE FOLDER
	run("Stack to Images");
	ImagesGreenedTitlesArray = getList("image.titles");
	for (i = 0; i < ImagesGreenedTitlesArray.length; i++){ 
		selectWindow(ImagesGreenedTitlesArray[i]);
		saveAs("Tiff", path_green + ImagesEnhancedTitlesArray[i] + ".tiff");
	};

	// USER INPUT: ARE YOU SATISFIED WITH THE CURRENT DISPLAY RANGE?
	Dialog.create(counter+1);
	Dialog.addNumber("Min:", manual_min);
	Dialog.addNumber("Max:", manual_max);
	Dialog.addCheckbox("Go again?", true);
	Dialog.show();
	manual_min = Dialog.getNumber();
	manual_max = Dialog.getNumber();
	retry = Dialog.getCheckbox();
	
	counter+=1;
	run("Close All");
	//close("B&C");

}

run("Close All");
close("B&C");

//print("done");
// this will make all the pop-ups show up again
setBatchMode(false);
