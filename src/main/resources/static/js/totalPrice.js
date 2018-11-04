document.addEventListener("DOMContentLoaded", function(event) {
	function displayTotalPrice() {
		const checkInField = document.getElementById("checkIn");
	    const checkOutField = document.getElementById("checkOut");
	    const priceElem = document.getElementById('price');
	    const totalElem = document.getElementById('total');
	    
	    const pricePerNight = parseFloat(priceElem.textContent, 10);
	    
	    checkInField.addEventListener("change", onCheckInDateChange);
	    checkOutField.addEventListener("change", onCheckOutDateChange);
	    
	    function getTotalPrice(dateIn, dateOut){
    		const checkIn = moment(dateIn);
    		const checkOut = moment(dateOut);
    		const days = checkOut.diff(checkIn, 'days');
    		if (days >= 1){
    			const total = days * pricePerNight;
	    		totalElem.innerHTML = "<span>Total to pay: <strong>" + total + "$</strong></span>";	
    		} else {
    			totalElem.innerHTML = "Check-out date must be after check-in date";
    		}	    				    	
	    }
	    
	    //we make the calculation only if both calendar fields are filled in
	    function onCheckInDateChange(event){
	    	const checkInDate = moment(event.target.value);
	    	if (checkOutField.value !== ""){
	    		getTotalPrice(checkInDate, checkOutField.value);
	    	}	    	
	    }
	    
	    function onCheckOutDateChange(event){
	    	const checkOutDate = moment(event.target.value);
	    	if (checkInField.value !== ""){
	    		getTotalPrice(checkInField.value, checkOutDate);
	    	}
	    }
	}
	displayTotalPrice();
});
