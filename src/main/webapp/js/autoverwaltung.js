/**
 * view-controller for autoverwaltung.html
 *
 * M133: Autoverwaltung
 *
 * @author  Assvin Shanmuganathan
 */

/**
 * register listeners and load all autos
 */
$(document).ready(function () {
    loadAutos();

    /**
     * listener for buttons within shelfForm
     */
    $("#shelfForm").on("click", "button", function () {
        if (confirm("Wollen Sie dieses Auto wirklich löschen?")) {
            deleteAutomodell(this.value);
        }
    });
});

function loadAutos() {
    $
        .ajax({
            url: "./resource/auto/list",
            dataType: "json",
            type: "GET"
        })
        .done(showAutos)
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 403) {
                window.location.href = "./login.html";
            } else if (xhr.status == 404) {
                $("#message").text("keine Autos vorhanden");
            }else {
                $("#message").text("Fehler beim Lesen der Autos");
            }
        })
}

/**
 * shows all autos as a table
 *
 * @param autoData all autos as an array
 */
function showAutos(autoData) {

    let table = document.getElementById("autoverwaltung");
    clearTable(table);

    $.each(autoData, function (uuid, auto) {

        if (auto.modellbezeichnung) {
            let row = table.insertRow(-1);
            let cell = row.insertCell(-1);
            cell.innerHTML = auto.modellbezeichnung;

            cell = row.insertCell(-1);
            cell.innerHTML = auto.automarke.au;

            cell = row.insertCell(-1);
            cell.innerHTML = auto.autokonzern.autokonzern;

            cell = row.insertCell(-1);
            cell.innerHTML = auto.preis;

            cell = row.insertCell(-1);
            cell.innerHTML = auto.gewicht;

            cell = row.insertCell(-1);
            cell.innerHTML = auto.leistung;

            cell = row.insertCell(-1);
            cell.innerHTML = auto.verbruach;

            cell = row.insertCell(-1);
            cell.innerHTML = "<a href='./autoedit.html?uuid=" + uuid + "'>Bearbeiten</a>";

            cell = row.insertCell(-1);
            cell.innerHTML = "<button type='button' id='delete_" + uuid + "' value='" + uuid + "'>Löschen</button>";
        }
    });
}

function clearTable(table) {
    while (table.hasChildNodes()) {
        table.removeChild(table.firstChild);
    }
}

/**
 * send delete request for a auto
 * @param autoUUID
 */
function deleteAutomodell(autoUUID) {
    $
        .ajax({
            url: "./resource/auto/delete?uuid=" + autoUUID,
            dataType: "text",
            type: "DELETE",
        })
        .done(function (data) {
            loadAutos();
            $("#message").text("Auto gelöscht");

        })
        .fail(function (xhr, status, errorThrown) {
            $("#message").text("Fehler beim Löschen des Autos");
        })
}
