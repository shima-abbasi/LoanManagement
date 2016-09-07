function addRow() {
    var grantName = document.getElementById("grantName");
    var minDuration = document.getElementById("minDuration");
    var maxDuration = document.getElementById("maxDuration");
    var minAmount = document.getElementById("minAmount");
    var maxAmount = document.getElementById("maxAmount");

    var table = document.getElementById("GrantConditionShowTable");
    var rowNumber = table.rows.length;
    if (grantName.value == "" || minDuration.value == "" || maxDuration.value == "" || minAmount.value == "" || maxAmount.value == "") {
        alert("لطفا اطلاعات ضروری را تکمیل کنید!")
    }
    else {
        if (parseInt(minDuration.value) > parseInt(maxDuration.value)) {
            alert("حداکثر مدت قرارداد باید بزرگتر از حداقل مدت قرارداد باشد.");
        }
        else if (parseFloat(minAmount.value) > parseFloat(maxAmount.value))
            alert("حداکثر مبلغ قرارداد باید بزرگتر از حداقل مدت قرارداد باشد.");
        else if (rowNumber == 0) {
            makeTable();
        }
        else {
            deleteFooter()
        }
    }

    if (grantName.value != "" && minAmount.value != "" && maxAmount.value != "" && minDuration.value != "" && maxDuration.value != "") {
        rowNumber = table.rows.length;
        var row = table.insertRow(rowNumber);
        row.insertCell(0).innerHTML = rowNumber.toString();
        row.insertCell(1).innerHTML = '<input type="text" name="grantName' + rowNumber + '" value="' + grantName.value + '" readonly >';
        row.insertCell(2).innerHTML = '<input type="text" name="minDuration' + rowNumber + '" value="' + minDuration.value + '" readonly>';
        row.insertCell(3).innerHTML = '<input type="text" name="maxDuration' + rowNumber + '" value="' + maxDuration.value + '" readonly>';
        row.insertCell(4).innerHTML = '<input type="text" name="minAmount' + rowNumber + '" value="' + minAmount.value + '" readonly>';
        row.insertCell(5).innerHTML = '<input type="text" name="maxAmount' + rowNumber + '" value="' + maxAmount.value + '" readonly>';
        row.insertCell(6).innerHTML = '<button  onClick="deleteRow(this)">حذف شرط</button>';
        addFooter()
    }
    document.getElementById("grantName").value = "";
    document.getElementById("minDuration").value = "";
    document.getElementById("maxDuration").value = "";
    document.getElementById("minAmount").value = "";
    document.getElementById("maxAmount").value = "";
}

function makeTable() {
    var table = document.getElementById("GrantConditionShowTable");
    var rowNumber = table.rows.length;
    var headerRow = table.insertRow(rowNumber);
    var headerCell = document.createElement("TH");
    headerCell.innerHTML = "ردیف";
    headerRow.appendChild(headerCell);
    headerCell = document.createElement("TH");
    headerCell.innerHTML = "نام شرط";
    headerRow.appendChild(headerCell);
    headerCell = document.createElement("TH");
    headerCell.innerHTML = "حداقل مدت قرارداد";
    headerRow.appendChild(headerCell);
    headerCell = document.createElement("TH");
    headerCell.innerHTML = "حداکثر مدت قرارداد";
    headerRow.appendChild(headerCell);
    headerCell = document.createElement("TH");
    headerCell.innerHTML = "حداقل مبلغ قرارداد";
    headerRow.appendChild(headerCell);
    headerCell = document.createElement("TH");
    headerCell.innerHTML = "حداکثر مبلغ قرارداد";
    headerRow.appendChild(headerCell);
    headerCell = document.createElement("TH");
    headerCell.innerHTML = "عملیات";
    headerRow.appendChild(headerCell);
}

function deleteRow(obj) {
    var index = obj.parentNode.parentNode.rowIndex;
    var table = document.getElementById("GrantConditionShowTable");
    table.deleteRow(index);
    deleteFooter();
    var rowNumber = table.rows.length;
    if (rowNumber == 1) {
        table.innerHTML = "";
    } else {
        for (var i = 1; i < rowNumber; i++) {
            table.rows[i].cells[0].innerHTML = i.toString();
        }
        addFooter();
    }
}

function addFooter() {
    var table = document.getElementById("GrantConditionShowTable");
    if (!table.tFoot) {
        var footer = table.createTFoot();
        var footerRow = footer.insertRow(0);
        var cell = footerRow.insertCell(0);
        cell.innerHTML = '<div align="center"><input type="submit" class="button" value=" ثبت شرایط " ></div> <input type="text" name="rowNumber" value="' + table.rows.length + '" hidden>';
    }
}

function deleteFooter() {
    var table = document.getElementById("GrantConditionShowTable");
    if (table.tFoot) {
        table.deleteTFoot();
    }
}