function init() {
    document.getElementById("first-table-select").innerHTML = ""
    document.getElementById("second-table-select").innerHTML = ""
    let dbName = document.getElementById("db-name").value
    console.log(dbName)
    var base_url = "http://localhost:4567/databases/"+dbName;
    $.ajax({
        url: base_url,
        method: 'get'
    }).then(function (data) {
        let dbContainer = document.getElementById("db-container")
        let firstTableContainer = document.getElementById("first-table-select")
        let secondTableContainer = document.getElementById("second-table-select")
        dbContainer.innerText = data
        let dataParsed = JSON.parse(data);
        console.log(dataParsed)
        if (data == null) {
            return
        }
        for (let table in dataParsed["data"]["tables"]) {
            console.log(table.toString())
            firstTableContainer.appendChild(createOption(table.toString()))
            secondTableContainer.appendChild(createOption(table.toString()))
        }
    });
}

async function tableOperation() {
    let dbName = document.getElementById("db-name").value;
    let firstTableContainer = document.getElementById("first-table-select")
    let secondTableContainer = document.getElementById("second-table-select")
    let t1Name = firstTableContainer.value;
    let t2Name = secondTableContainer.value;
    let url = "http://localhost:4567/databases/"+dbName+"/tables/" + t1Name + "/diff/" + t2Name;
    const response = await fetch(url, {
        method: 'GET', // *GET, POST, PUT, DELETE, etc.
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer'
    }).catch((error) =>
    {
        console.log(error)
    });
    console.log(response.body)
    init();
}

function createOption(name) {
    const option = document.createElement("option")
    option.value = name;
    option.innerText = name;
	return option;
}

