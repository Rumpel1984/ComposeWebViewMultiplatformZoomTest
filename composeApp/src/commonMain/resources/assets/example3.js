var tableLayouts
var tableSelectable

// ISSUE: SVG Document is not properly linked when using OBJECT
var svgObj = document.getElementById("svgObject")

svgObj.addEventListener("load",function(){

    var svgDoc = svgObj.contentDocument;

    // Add onClick listener for all items
    svgDoc.addEventListener("click", doSomething);

    // Remove all style elements directly assigned to PATH elements
    var tableSelectablePaths = svgDoc.querySelectorAll(".Location > path, .Location > rect, .Merchant > path, .Merchant > rect, .Room > path, .Room > rect, .Food > path, .Food > rect, .Artist > path, .Artist > rect");
    var item;
    for (item = 0; item < tableSelectablePaths.length; item++) {
        tableSelectablePaths[item].removeAttribute("style");
    }

    // Get all Layouts for enabling to switch between them
    var tableLayouts = svgDoc.querySelectorAll('*[id^=layer_]');

    // Get all Selectable objects for clicking
    var tableSelectable = svgDoc.querySelectorAll(".Location, .Merchant, .Room, .Food, .Artist, .WC, .Escalator");

}


// onClick Function
function doSomething(e) {
    if (e.target !== e.currentTarget) {
        var item;
        for (item = 0; item < tableSelectable.length; item++) {
            var svgPath = svgDoc.getElementById(tableSelectable[item].id);

            if (svgPath.classList.contains("selected")) {
                svgPath.classList.remove("selected");
				//alert("Unselect: "+svgPath.id);
				window.kmpJsBridge.callNative("HideDialog",JSON.stringify(tableSelectable[item].id));
            } else if (e.target.id === tableSelectable[item].id) {
				svgPath.classList.add("selected");
				svgPath.scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' })
				//alert("Select: "+tableSelectable[item].id);
				window.kmpJsBridge.callNative("ShowDialog",JSON.stringify(tableSelectable[item].id));
			} else if (e.target.parentElement.id === tableSelectable[item].id) {
				svgPath.classList.add("selected");
				svgPath.scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' })
				//alert("Select: "+tableSelectable[item].id);
				window.kmpJsBridge.callNative("ShowDialog",JSON.stringify(tableSelectable[item].id));
			} else  if (e.target.parentElement.parentElement.id === tableSelectable[item].id) {
				svgPath.classList.add("selected");
				svgPath.scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' })
				//alert("Select: "+tableSelectable[item].id);
				window.kmpJsBridge.callNative("ShowDialog",JSON.stringify(tableSelectable[item].id));
			}

        }
    }
    e.stopPropagation();
}

function showLayer(layer) {
	var item;
    for (item = 0; item < tableLayouts.length; item++) {
        var svgPath = svgDoc.getElementById(tableLayouts[item].id);
        svgPath.style.display = "none";
        if (layer === tableLayouts[item].id) {
            svgPath.style.display = "inline";
        }
    }
}

function focusElement(layer, element) {
    showLayer(layer);
    unfocusElements();
    setTimeout(() => {
        var selectedElement = svgDoc.getElementById(element);
        selectedElement.classList.toggle("selected");
        selectedElement.scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' })
    }, 250);
}

function unfocusElements() {
    var item;
    for (item = 0; item < tableSelectable.length; item++) {
        var svgPath = svgDoc.getElementById(tableSelectable[item].id);
        if (svgPath.classList.contains("selected")) {
            svgPath.classList.remove("selected");
        }
    }
}