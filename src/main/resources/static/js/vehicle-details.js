

document.addEventListener("DOMContentLoaded", function(event) {
	function displaySg() {
	    const bottom = document.getElementById("reserve");
	    const form = document.getElementById("form-container");
	    bottom.addEventListener("click",showSg);
	    function showSg(event){
	    	form.style.display = "block";
	    }
	}
	displaySg(); // don't forget to call this function on the first run
});