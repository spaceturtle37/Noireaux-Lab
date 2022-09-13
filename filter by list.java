path_scripts = "J:/Lab members/graduate students/Velasco/ImageJ Scripts/";

folder1 = "Histograms/";
folder2 = "Image Processing/";
folder3 = "Kinetic Video/";

ending = ".java";

// .* fills in blanks for naming conventions
// works as skip operator, sarching for string with the given format
// order matters
// close with ( ) on the sides

filter_array = newArray("(.*FITC.*s1.*)","(.*FITC.*s2.*)");
nfilters = filter_array.length;
//Array.print(filter_array);
//print(nfilters);

for (i = 0; i < nfilters; i++){
	//print(i);
	//print(filter_array[i]); 
	runMacro( path_scripts + folder2 + "FITC processing arg" + ending , filter_array[i] );
};




//runMacro( path_scripts + folder3 + "just 1 video" + ending );
//runMacro( path_scripts + folder1 + "vertical histogram mean std" + ending );