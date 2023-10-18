// Funktion för att skapa en ny enkät
function createSurvey() {
    const surveyName = document.getElementById("surveyName").value;

    // Kontrollera om surveyName är tomt
    if (!surveyName) {
        alert("Vänligen fyll i ett enkätnamn!");
        return;
    }

    // URL till din backend-tjänst för att skapa en enkät
    const url = "http://localhost:8080/surveys";

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: surveyName
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            alert("Enkät skapad!");
            fetchSurveys();  // Uppdatera enkätlistan när en ny enkät har skapats
        })
        .catch(error => {
            alert("Det uppstod ett fel när enkäten skulle skapas: " + error);
        });
}

// Funktion för att hämta alla enkäter och visa dem i en lista
function fetchSurveys() {
    const url = "http://localhost:8080/surveys/getAllSurveys";

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            const surveyList = document.getElementById("surveyList");
            surveyList.innerHTML = ''; // Rensa befintlig lista

            data.forEach(survey => {
                const listItem = document.createElement("li");
                listItem.textContent = survey.title;
                const addButton = document.createElement("button");
                addButton.textContent = "Lägg till fråga";
                addButton.onclick = () => addQuestionToSurvey(survey.id);
                listItem.appendChild(addButton);
                surveyList.appendChild(listItem);
            });
        })
        .catch(error => {
            alert("Det uppstod ett fel när enkäterna skulle hämtas: " + error);
        });
}

// Funktion för att lägga till en fråga i en specifik enkät
let currentSurveyId = null;

function addQuestionToSurvey(surveyId) {
    // Spara den nuvarande enkätens ID
    currentSurveyId = surveyId;

    // Visa modal
    $('#addQuestionModal').modal('show');
}

// Event Listener för att spara frågan när "Spara fråga"-knappen klickas
document.getElementById("saveQuestionBtn").addEventListener("click", function() {
    const questionText = document.getElementById("questionText").value;
    const questionType = document.getElementById("questionType").value;

    // Kontrollera om questionText är tomt
    if (!questionText) {
        alert("Vänligen fyll i frågetexten!");
        return;
    }

    const url = "http://localhost:8080/surveys/" + currentSurveyId + "/questions";

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            questionText: questionText,
            type: questionType
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            alert("Fråga tillagd!");
            // Stäng modal
            $('#addQuestionModal').modal('hide');
            // Rensa inmatningsfält i modal
            document.getElementById("questionText").value = '';
            document.getElementById("questionType").value = 'SINGLE_CHOICE';
            fetchSurveys();
        })
        .catch(error => {
            alert("Det uppstod ett fel när frågan skulle läggas till: " + error);
        });
});

// Event Listener för att skapa en ny enkät
document.getElementById("createSurveyBtn").addEventListener("click", createSurvey);

// Initialiseringskod
fetchSurveys();
