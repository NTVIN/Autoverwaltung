/**
 * view-controller for bookedit.html
 *
 * M133: Autoverwaltung
 *
 * @author  Assvin Shanmuganathan
 */

/**
 * register listeners and load the auto data
 */
$(document).ready(function () {
    loadAuto();
    loadAutomarke();
    loadAutokonzern();


    /**
     * listener for submitting the form
     */
    $("#autoeditForm").submit(saveAuto());

    /**
     * listener for button [abbrechen], redirects to bookshelf
     */
    $("#cancel").click(function () {
        window.location.href = "./autoverwaltung.html";
    });
});

/**
 *  loads the data of this auto
 *
 */
function loadAuto() {
    let autoUUID = $.urlParam("uuid");
    if (autoUUID !== null) {
        $
            .ajax({
                url: "./resource/auto/read?uuid=" + autoUUID,
                dataType: "json",
                type: "GET"
            })
            .done(showAutos())
            .fail(function (xhr, status, errorThrown) {
                if (xhr.status == 403) {
                    window.location.href = "./login.html";
                } else if (xhr.status == 404) {
                    $("#message").text("Kein Auto gefunden");
                } else {
                    window.location.href = "./autoverwaltung.html";
                }
            })
    }
}

/**
 * shows the data of this auto
 * @param  auto  the auto data to be shown
 */
function showAuto(auto) {
    $("#autoUUID").val(auto.autoUUID);
    $("#modellbezeichnung").val(auto.modellbezeichnung);
    $("#preis").val(auto.preis);
    $("#gewicht").val(auto.gewicht);
    $("#leistung").val(auto.leistung);
    $("#automarke").val(auto.automarke.automarkeUUID);
    $("#autokonzern").val(auto.autokonzern.konzernUUID);

}

/**
 * sends the auto data to the webservice
 * @param form the form being submitted
 */
function saveAuto(form) {
    form.preventDefault();

    let url = "./resource/auto/";
    let type;

    let autoUUID = $("#autoUUID").val();
    if (autoUUID) {
        type= "PUT";
        url += "update"
    } else {
        type = "POST";
        url += "create";
    }

    $
        .ajax({
            url: url,
            dataType: "text",
            type: type,
            data: $("#autoeditForm").serialize()
        })
        .done(function (jsonData) {
            window.location.href = "./autoverwaltung.html"
        })
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Dieses Auto existiert nicht");
            } else {
                $("#message").text("Fehler beim Speichern des Autos");
            }
        })
}

function loadAutomarke() {
    $
        .ajax({
            url: "./resource/automarke/list",
            dataType: "json",
            type: "GET"
        })
        .done(showAutomarke())
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Keine Automarken gefunden");
            } else {
                window.location.href = "./autoverwaltung.html";
            }
        })
}

function showAutomarke(automarke) {

    $.each(automarke, function (uuid, automarke) {
        $('#automarke').append($('<option>', {
            value: automarke.automarkeUUID,
            text: automarke.automarke
        }));
    });
}

function loadAutokonzern() {
    $
        .ajax({
            url: "./resource/autokonzern/list",
            dataType: "json",
            type: "GET"
        })
        .done(showAutokonzern())
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Keine Konzerne gefunden");
            } else {
                window.location.href = "./autoverwaltung.html";
            }
        })
}

function showAutokonzern(autokonzern) {

    $.each(autokonzern, function (uuid, automarke) {
        $('#autokonzern').append($('<option>', {
            value: autokonzern.konzernUUID,
            text: autokonzern.autokonzern
        }));
    });
}

