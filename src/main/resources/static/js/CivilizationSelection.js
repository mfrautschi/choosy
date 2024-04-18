function addToList() {
    // Get element
    let srcList = document.getElementById("civ-selection");
    let selectedValue = srcList.options[srcList.selectedIndex].value;
    let selectedText = srcList.options[srcList.selectedIndex].text;

    // Add element to civ-selected
    let destList = document.getElementById("civ-selected");
    let opt = document.createElement('option');
    opt.value = selectedValue;
    opt.text = selectedText;
    destList.appendChild(opt);

    //Remove element from civ-selection
    srcList.options[srcList.selectedIndex].remove();
}

function revertFromList() {
    let destList = document.getElementById("civ-selected");
    let selectedValue = destList.options[destList.selectedIndex].value;
    let selectedText = destList.options[destList.selectedIndex].text;

    // Add element to civ-selection
    let srcList = document.getElementById("civ-selection");
    let opt = document.createElement('option');
    opt.value = selectedValue;
    opt.text = selectedText;
    srcList.appendChild(opt);

    // Remove element from civ-selected
    destList.options[destList.selectedIndex].remove();
}