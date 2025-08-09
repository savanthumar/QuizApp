let timeLeft = 30 * 60; // 30 minutes in seconds
let switches = 0;
let testEnded = false; // Flag to check if test is over

function startTimer() {
    const t = document.getElementById('time');
    const timer = setInterval(function () {
        let m = Math.floor(timeLeft / 60);
        let s = timeLeft % 60;
        t.textContent = m + ':' + (s < 10 ? '0' + s : s);
        timeLeft--;
        if (timeLeft < 0) {
            clearInterval(timer);
            alert('Time is up! Submitting test.');
            testEnded = true;
            document.getElementById('testForm').submit();
        }
    }, 1000);
}

window.onload = startTimer;

// Warn before leaving if test not ended
window.onbeforeunload = function () {
    if (!testEnded) {
        return "Test in progress. Leaving will submit the test.";
    }
};

// Prevent back navigation
history.pushState(null, null, location.href);
window.onpopstate = function () {
    if (!testEnded) {
        history.pushState(null, null, location.href);
        alert('Back navigation is disabled during the test.');
    }
};

// Detect tab switch, but ignore if test ended
document.addEventListener('visibilitychange', function () {
    if (!testEnded && document.hidden) {
        switches++;
        if (switches >= 3) {
            alert('Switched tabs too many times. Submitting test.');
            testEnded = true;
            document.getElementById('testForm').submit();
        } else {
            alert('Do not switch tabs during the test. Attempts: ' + switches);
        }
    }
});

// Mark test ended when form is submitted
document.getElementById('testForm').addEventListener('submit', function () {
    testEnded = true;
});
