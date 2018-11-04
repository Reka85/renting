
document.addEventListener("DOMContentLoaded", function(event) {
	function displayReservationForm() {
	    const reservationForm = document.getElementById("form-container");
	    const reserveButton = document.getElementById("reserve");
	    reserveButton.addEventListener("click",showForm);
	    
	    function showForm(event){
	    	reservationForm.style.display = "block";
	    }
	}
	displayReservationForm();
});