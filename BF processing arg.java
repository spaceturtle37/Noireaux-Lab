setBatchMode(true);
run("Close All");
close("B&C");

root = "C:/Users/Jcv5ff/Desktop/";
path_in = root + "Pool/";
//path_out = root + "Well/BF/";
path_out = root + "Well/";

// look for output folder, if it doesnt exist make it
if (File.exists(path_out)==0) {
	File.makeDirectory(path_out);
};

dup1 = "_1";
channel = "BF";
//filter = channel;
filter = getArgument();
//filter = "(.*" + getArgument() + ".*)";

// open and duplicate

run("Image Sequence...", "dir=" + path_in + " filter=" + filter + " sort use ");
name = getTitle();
run("Duplicate...", "title=[" + name + dup1 + "] duplicate");
selectWindow(name);
close();
selectWindow(name+dup1);


//run statistics
//run("Histogram", "stack");
//selectWindow(name + "-1");
run("Brightness/Contrast...");
//waitForUser("Readjust contrast");
run("Apply LUT", "stack");
selectWindow(name + dup1);
run("Image Sequence... ", "dir=" + path_out + "/" + " format=TIFF use" );

run("Close All");
close("B&C");


//print("done");
// this will make all the pop-ups show up again
setBatchMode(false);
