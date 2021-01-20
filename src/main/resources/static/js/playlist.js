function getPlaylist(id) {
    fetch('http://localhost:8082/playlists/read/' + id)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }

                // Examine the text in the response
                response.json().then(function (playlistData) {
                    console.log(playlistData)
                    let title = document.getElementById("playlistTitle");
                    title.innerHTML = '';

                    let description = document.getElementById("description");
                    description.innerHTML = '';

                    let user = document.getElementById("userNameDiv");
                    user.innerHTML = '';
                    //artist.innerHTML = "Created by: " + albumData["artist"]["name"];
                    user.insertAdjacentHTML("beforeend", '<p id="userName"> Created by: ' + playlistData["user"]["username"] + '</p>');
                    title.insertAdjacentHTML("beforeend", playlistData["name"]);
                    description.insertAdjacentHTML("beforeend", "Description: " + playlistData["description"]);

                    let trackList = playlistData["tracks"];
                    let table = document.getElementById("table")
                    for (let track of trackList) {
                        table.appendChild(trackRow(track["id"], track["name"], duration(track["duration"])) )
                    }


                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function trackRow(id, name, duration) {
    const a = document.createElement("a")
    a.href = "/one_track.html?id=" + id;

    const pid = document.createElement("p")
    pid.innerText = id
    a.appendChild(pid)

    const pname = document.createElement("p")
    pname.innerText = name
    a.appendChild(pname)

    const pduration = document.createElement("p")
    pduration.innerText = duration
    a.appendChild(pduration)

    return a
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');
console.log(id);

getPlaylist(id);