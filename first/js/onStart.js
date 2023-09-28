function loadTableDataFromCookie() {
    const savedData = Cookies.get("tableData");
    console.log(savedData);
    if (savedData) {
        const tableData = JSON.parse(savedData);
        for (let i = 0; i < tableData.length; i++) {
            const rowData = tableData[i];
            let row = "<tr class='fade-in'>";
            for (let j = 0; j < rowData.length; j++)
                row += `<td>${rowData[j]}</td>`;
            row += "</tr>";
            $("#result tbody").append(row);
            setTimeout(function () {
                $(".fade-in").addClass("active");
            }, 10);
        }
    }
}

$(document).ready(function () {
    loadTableDataFromCookie();
});