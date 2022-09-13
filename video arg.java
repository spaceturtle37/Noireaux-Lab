//************************************************************************
// ************************** Making videos ******************************
//************************************************************************

// this will make all the pop-ups not show up
setBatchMode(true);

// set frame rate for videos
framerate =  5;

//print(getArgument());

root_folder = "C:/Users/Jcv5ff/Desktop/";
//path_in = root_folder + "Well/" + getArgument() + "/";
path_in = root_folder + "Well/";
path_out = root_folder + "Well/Videos/";
//name = getArgument();
filter = getArgument();
//filter = "(.*" + getArgument() + ".*)";
//name = filter;
//name = "video";
//name = getArgument();

// look for output folder, if it doesnt exist make it
if (File.exists(path_out)==0) {
	File.makeDirectory(path_out);
};

	
//run("Image Sequence...", " dir=" + path_in + " sort use");
run("Image Sequence...", "dir=" + path_in + " filter=" + filter + " sort use ");

// get name from first slice
name = getInfo("slice.label");
//print(name);

//run("Duplicate...", "title=[temp] duplicate");
//selectWindow();
//close();
//selectWindow("temp");
run("AVI... ", "compression=JPEG frame=" + framerate + " save=[" + path_out + name + ".AVI]");
run("Close All");
//close();

//print("done");
// this will make all the pop-ups show up again
setBatchMode(false);
