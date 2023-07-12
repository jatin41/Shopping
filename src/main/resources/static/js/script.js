document.addEventListener('DOMContentLoaded', function() {
  const animatedElements = document.querySelectorAll('.animated-text');
  animatedElements.forEach(function(element) {
    element.style.animationDelay = '0.5s';
  });
});

function toggleDarkMode() {
  const body = document.body;
  const darkModeToggle = document.querySelector('.dark-mode-toggle');

  body.classList.toggle('dark-mode');
  darkModeToggle.classList.toggle('dark-mode');
}

document.addEventListener('DOMContentLoaded', function() {
  const darkModeToggle = document.querySelector('.dark-mode-toggle');
  darkModeToggle.addEventListener('click', toggleDarkMode);
});
