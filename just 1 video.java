//************************************************************************
// ************************** Making videos ******************************
//************************************************************************

// this will make all the pop-ups not show up
setBatchMode(true);

// set frame rate for videos
framerate =  5;

root_folder = "C:/Users/Jcv5ff/Desktop/";
path_in = root_folder + "Well/FITC_green/";
path_out = root_folder + "Well/Videos/";
video_name = "video";

// look for output folder, if it doesnt exist make it
if (File.exists(path_out)==0) {
	File.makeDirectory(path_out);
};
	
//run("Image Sequence...", "select=" + path_in + " dir=" + path_in + " sort use");
run("Image Sequence...", " dir=" + path_in + " sort use");


//run("Duplicate...", "title=[temp] duplicate");
//selectWindow();
//close();
//selectWindow("temp");
run("AVI... ", "compression=JPEG frame=" + framerate + " save=[" + path_out + video_name + ".AVI]");
close();

//print("done");
// this will make all the pop-ups show up again
setBatchMode(false);
