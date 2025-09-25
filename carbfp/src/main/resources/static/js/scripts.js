// Function to create activity chart
function createActivityChart(labels, values) {
    const ctx = document.getElementById("activityChart");
    if (!ctx) return; // Skip if not on dashboard page

    new Chart(ctx, {
        type: "bar",
        data: {
            labels: labels,
            datasets: [{
                label: "CO₂ Emission (kg)",
                data: values,
                backgroundColor: [
                    "rgba(33, 150, 243, 0.6)",
                    "rgba(76, 175, 80, 0.6)",
                    "rgba(255, 193, 7, 0.6)",
                    "rgba(244, 67, 54, 0.6)"
                ],
                borderColor: [
                    "#2196f3",
                    "#4caf50",
                    "#ffc107",
                    "#f44336"
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    display: true,
                    position: "top"
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: { stepSize: 10 }
                }
            }
        }
    });
}

// Example extra UI feedback (success alerts)
function showSuccessMessage(message) {
    const alertBox = document.createElement("div");
    alertBox.className = "alert success";
    alertBox.innerText = message;
    document.body.prepend(alertBox);
    setTimeout(() => alertBox.remove(), 3000);
}
