path_scripts = "J:/Lab members/graduate students/Velasco/ImageJ Scripts/";

folder1 = "Histograms/";
folder2 = "Image Processing/";
folder3 = "Kinetic Video/";

ending = ".java";

runMacro( path_scripts + folder3 + "unpack slices" + ending );
runMacro( path_scripts + folder3 + "sort subfolders" + ending );
runMacro( path_scripts + folder3 + "make videos" + ending );