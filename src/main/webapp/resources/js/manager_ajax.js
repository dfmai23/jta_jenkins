

function getALLOpenReimbursements() {
	var xhr = new XMLHttpRequest();		//1. create XMLHttpRequest object
	
	xhr.onreadystatechange = function() {	//2. define onreadystatechange
	  if(this.readyState == 4 && this.status == 200){
	    console.log(this.response);
	    createAllPendingTable(JSON.parse(this.response));
	  }
	}
	//url is where we will get XML message that is stored on the Web server
	xhr.open("GET", "all_open_reimbursements.ajax", true);	//3. open the connection 
	xhr.send();	//4. send the connection
}


function createAllPendingTable(json) {
    var colnames = [];
    var col = [];
    for(var i=0; i<json.length; i++) {	//extract table headers
    	for (var key in json[i]) {
    		if (col.indexOf(key) === -1) {
    			if (key == "id") {
    				colnames.push("Reimbursement ID");
    			}
    			if (key == "startDate") {
    				colnames.push("Start Date");
    			}
    			if (key == "amount") {
    				colnames.push("Amount");
    			}
    			if (key == "description") {
    				colnames.push("Description");
    			}
    			//console.log(key);
    			col.push(key);
    		}
    	}
    }
    
    var table = document.createElement("table");	//create dynamic table
    table.classList.add("table");
    table.classList.add("table-stripe");
    table.classList.add("table-hover");
    table.classList.add("table-bordered");
    table.classList.add("table-responsive");
    //table.class = "table table-striped table-hover table-responsive table-bordered";	//add tags
    
    var tr = table.insertRow(-1);        			//create html table headers
    for (var i = 0; i < colnames.length; i++) {			//insert headers
        var th = document.createElement("th"); 
        th.innerHTML = colnames[i];					
        tr.appendChild(th);
    }

    for (var i = 0; i < json.length; i++) {	//add json data row by row
        tr = table.insertRow(-1);			//ins empty row
        for (var j = 0; j < col.length; j++) {
            if (col[j] == "id" || col[j]=='startDate' || col[j]=='amount' || col[j]=='description') {	
            	var tabCell = tr.insertCell(-1);    
            	if (col[j] == "startDate" || col[j] == "endDate") {
	            	//console.log(parseDate(json[i][col[j]]));
	            	tabCell.innerHTML = parseDate(json[i][col[j]]);
	            } 
	            else {
	            	tabCell.innerHTML =json[i][col[j]];
	            }
        	}
        }
    }

    var divContainer = document.getElementById("dynamic_reTable");	//add newly created table to container
    divContainer.innerHTML = "";
    divContainer.appendChild(table);
}