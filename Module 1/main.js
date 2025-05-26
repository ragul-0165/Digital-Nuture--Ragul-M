/* Section: Global Variables and Configuration */
let eventData = [
    {
        id: 1,
        title: "Digital Photography Workshop",
        type: "workshop",
        date: "2024-02-15",
        time: "10:00 AM",
        location: "Community Center",
        description: "Learn the fundamentals of digital photography with professional equipment and expert guidance.",
        image: "https://pixabay.com/get/g247b10d619547689b3516841dab88a9266393b6283fb6baade7cce37e7b24bbcf2dc9555355692a6a98e2cb0b62e2f98ba5f7a46ce613db4f9b7bc852968443e_1280.jpg",
        fee: 25,
        capacity: 20,
        registered: 12
    },
    {
        id: 2,
        title: "Summer Music Festival",
        type: "festival",
        date: "2024-02-20",
        time: "6:00 PM",
        location: "City Park",
        description: "Join us for an evening of live music featuring local bands and food trucks.",
        image: "https://pixabay.com/get/g597f904d1b13b66bfaff1683e678fff6d9ccb01c9e975a8f699f171e02b008f7d0c4b558815512fe9aefc4548dc2fdd47eb84ba58836bae0fc505614bb52e5a7_1280.jpg",
        fee: 15,
        capacity: 200,
        registered: 145
    },
    {
        id: 3,
        title: "Tech Networking Meetup",
        type: "meetup",
        date: "2024-02-25",
        time: "7:00 PM",
        location: "Innovation Hub",
        description: "Connect with local tech professionals and entrepreneurs in a casual setting.",
        image: "https://pixabay.com/get/g281ee4fcbe5a47fdd9f6fa4e5b2aa8d91e724ed2cb88d4ee238ef2d2cd4a08ce7fbe958a82c8d530affcd1630752e096a36118d631985602f27b4d7a8709997e_1280.jpg",
        fee: 0,
        capacity: 50,
        registered: 32
    },
    {
        id: 4,
        title: "Community Garden Cleanup",
        type: "charity",
        date: "2024-03-01",
        time: "9:00 AM",
        location: "Riverside Garden",
        description: "Help beautify our community garden while supporting local environmental initiatives.",
        image: "https://pixabay.com/get/g864e7846050629fadc1714c3b63e916573eba0d1ad1477313e283a4c14610ee48b550b31ae2a6555bbde58f6760c1389ceb32330e97d7087e5aab7b9f0a27aec_1280.jpg",
        fee: 10,
        capacity: 30,
        registered: 18
    }
];

/* Section: DOM Content Loaded Events */
document.addEventListener('DOMContentLoaded', function() {
    // Initialize page-specific functionality
    initializeCommonFeatures();
    
    // Load user preferences from localStorage
    loadUserPreferences();
    
    // Add smooth scrolling to anchor links
    addSmoothScrolling();
});

/* Section: Common Feature Initialization */
function initializeCommonFeatures() {
    // Set up geolocation button if it exists
    const locationBtn = document.getElementById('getLocationBtn');
    if (locationBtn) {
        locationBtn.addEventListener('click', getUserLocation);
    }
    
    // Initialize form validations
    initializeFormValidations();
    
    // Set up video controls if video page
    if (document.getElementById('mainVideo')) {
        initializeVideoControls();
    }
}

/* Section: Geolocation API Implementation */
function getUserLocation() {
    const locationBtn = document.getElementById('getLocationBtn');
    const locationSection = document.getElementById('location');
    const locationDisplay = document.getElementById('locationDisplay');
    
    if (!navigator.geolocation) {
        alert('Geolocation is not supported by this browser.');
        return;
    }
    
    // Update button to show loading state
    locationBtn.innerHTML = '<i class="bi bi-arrow-clockwise spin me-2"></i>Getting Location...';
    locationBtn.disabled = true;
    
    const options = {
        enableHighAccuracy: true,
        timeout: 10000,
        maximumAge: 300000 // 5 minutes
    };
    
    navigator.geolocation.getCurrentPosition(
        function(position) {
            const lat = position.coords.latitude.toFixed(6);
            const lon = position.coords.longitude.toFixed(6);
            const accuracy = Math.round(position.coords.accuracy);
            
            // Display coordinates
            locationDisplay.innerHTML = `
                <strong>Latitude:</strong> ${lat} | 
                <strong>Longitude:</strong> ${lon} | 
                <strong>Accuracy:</strong> ±${accuracy}m
            `;
            
            // Show location section
            locationSection.classList.remove('d-none');
            
            // Save to localStorage
            localStorage.setItem('userLocation', JSON.stringify({
                lat: lat,
                lon: lon,
                timestamp: new Date().toISOString()
            }));
            
            // Reset button
            locationBtn.innerHTML = '<i class="bi bi-geo-alt me-2"></i>Update Location';
            locationBtn.disabled = false;
        },
        function(error) {
            let errorMessage = 'Unable to retrieve location: ';
            switch(error.code) {
                case error.PERMISSION_DENIED:
                    errorMessage += 'Location access denied by user.';
                    break;
                case error.POSITION_UNAVAILABLE:
                    errorMessage += 'Location information unavailable.';
                    break;
                case error.TIMEOUT:
                    errorMessage += 'Location request timed out.';
                    break;
                default:
                    errorMessage += 'Unknown error occurred.';
                    break;
            }
            
            alert(errorMessage);
            
            // Reset button
            locationBtn.innerHTML = '<i class="bi bi-geo-alt me-2"></i>Find Nearby';
            locationBtn.disabled = false;
        },
        options
    );
}

/* Section: Image Enlargement Functionality */
function enlargeImage(img) {
    const modal = document.getElementById('imageModal');
    const modalImage = document.getElementById('modalImage');
    
    if (modal && modalImage) {
        modalImage.src = img.src;
        modalImage.alt = img.alt;
        
        const bsModal = new bootstrap.Modal(modal);
        bsModal.show();
    }
}

/* Section: Event Loading and Display */
function loadEvents() {
    const container = document.getElementById('eventsContainer');
    if (!container) return;
    
    container.innerHTML = '';
    
    eventData.forEach(event => {
        const eventCard = createEventCard(event);
        container.appendChild(eventCard);
    });
}

function createEventCard(event) {
    const col = document.createElement('div');
    col.className = 'col-lg-6 col-xl-4';
    
    const progressPercentage = (event.registered / event.capacity) * 100;
    const isAlmostFull = progressPercentage > 80;
    const isFree = event.fee === 0;
    
    col.innerHTML = `
        <div class="card eventCard h-100 border-0 shadow-sm">
            <img src="${event.image}" class="card-img-top" alt="${event.title}" style="height: 200px; object-fit: cover;">
            <div class="card-body d-flex flex-column">
                <div class="d-flex justify-content-between align-items-start mb-2">
                    <span class="badge bg-primary">${event.type.charAt(0).toUpperCase() + event.type.slice(1)}</span>
                    <span class="badge ${isFree ? 'bg-success' : 'bg-warning text-dark'}">
                        ${isFree ? 'Free' : '$' + event.fee}
                    </span>
                </div>
                
                <h5 class="card-title">${event.title}</h5>
                <p class="card-text text-muted flex-grow-1">${event.description}</p>
                
                <div class="event-details">
                    <div class="d-flex align-items-center mb-2">
                        <i class="bi bi-calendar me-2 text-primary"></i>
                        <small>${formatDate(event.date)} at ${event.time}</small>
                    </div>
                    <div class="d-flex align-items-center mb-3">
                        <i class="bi bi-geo-alt me-2 text-primary"></i>
                        <small>${event.location}</small>
                    </div>
                    
                    <div class="mb-3">
                        <div class="d-flex justify-content-between mb-1">
                            <small>Registration Progress</small>
                            <small>${event.registered}/${event.capacity}</small>
                        </div>
                        <div class="progress" style="height: 6px;">
                            <div class="progress-bar ${isAlmostFull ? 'bg-warning' : 'bg-success'}" 
                                 style="width: ${progressPercentage}%"></div>
                        </div>
                        ${isAlmostFull ? '<small class="text-warning">Almost full!</small>' : ''}
                    </div>
                </div>
                
                <div class="d-flex gap-2">
                    <button class="btn btn-primary flex-grow-1" onclick="showEventDetails(${event.id})">
                        View Details
                    </button>
                    <a href="register.html" class="btn btn-outline-success">
                        <i class="bi bi-person-plus"></i>
                    </a>
                </div>
            </div>
        </div>
    `;
    
    return col;
}

/* Section: Event Detail Modal */
function showEventDetails(eventId) {
    const event = eventData.find(e => e.id === eventId);
    if (!event) return;
    
    const modal = document.getElementById('eventModal');
    const modalTitle = document.getElementById('modalEventTitle');
    const modalContent = document.getElementById('modalEventContent');
    
    modalTitle.textContent = event.title;
    modalContent.innerHTML = `
        <div class="row">
            <div class="col-md-6">
                <img src="${event.image}" class="img-fluid rounded mb-3" alt="${event.title}">
            </div>
            <div class="col-md-6">
                <h6>Event Details</h6>
                <p class="mb-2"><strong>Date:</strong> ${formatDate(event.date)}</p>
                <p class="mb-2"><strong>Time:</strong> ${event.time}</p>
                <p class="mb-2"><strong>Location:</strong> ${event.location}</p>
                <p class="mb-2"><strong>Fee:</strong> ${event.fee === 0 ? 'Free' : '$' + event.fee}</p>
                <p class="mb-3"><strong>Available Spots:</strong> ${event.capacity - event.registered}</p>
                
                <h6>Description</h6>
                <p>${event.description}</p>
            </div>
        </div>
    `;
    
    const bsModal = new bootstrap.Modal(modal);
    bsModal.show();
}

/* Section: Form Validation Functions */
function initializeFormValidations() {
    // Get all forms that need validation
    const forms = document.querySelectorAll('.needs-validation, form[novalidate]');
    
    forms.forEach(form => {
        form.addEventListener('submit', function(event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        });
    });
}

function validatePhone(input) {
    const phonePattern = /^\d{10}$/;
    const isValid = phonePattern.test(input.value.replace(/\D/g, ''));
    
    if (input.value && !isValid) {
        input.setCustomValidity('Please enter a valid 10-digit phone number');
        input.classList.add('is-invalid');
    } else {
        input.setCustomValidity('');
        input.classList.remove('is-invalid');
    }
}

function updateEventFee() {
    const eventTypeSelect = document.getElementById('eventType');
    const feeDisplay = document.getElementById('feeDisplay');
    const feeAmount = document.getElementById('feeAmount');
    
    if (!eventTypeSelect || !feeDisplay || !feeAmount) return;
    
    const selectedOption = eventTypeSelect.options[eventTypeSelect.selectedIndex];
    const fee = selectedOption.getAttribute('data-fee');
    
    if (fee !== null) {
        feeAmount.textContent = fee;
        feeDisplay.classList.remove('d-none');
        
        // Save preference to localStorage
        localStorage.setItem('preferredEventType', eventTypeSelect.value);
    } else {
        feeDisplay.classList.add('d-none');
    }
}

function trackTextareaInput(textarea) {
    const charCount = document.getElementById('charCount');
    if (charCount) {
        charCount.textContent = textarea.value.length;
    }
}

function confirmRegistration(event) {
    event.preventDefault();
    
    const form = document.getElementById('registrationForm');
    const output = document.getElementById('registrationOutput');
    
    if (form.checkValidity()) {
        // Show confirmation
        output.classList.remove('d-none');
        
        // Save registration data to localStorage
        const registrationData = {
            name: document.getElementById('fullName').value,
            email: document.getElementById('email').value,
            phone: document.getElementById('phone').value,
            eventDate: document.getElementById('eventDate').value,
            eventType: document.getElementById('eventType').value,
            timestamp: new Date().toISOString()
        };
        
        let registrations = JSON.parse(localStorage.getItem('registrations') || '[]');
        registrations.push(registrationData);
        localStorage.setItem('registrations', JSON.stringify(registrations));
        
        // Reset form after short delay
        setTimeout(() => {
            form.reset();
            form.classList.remove('was-validated');
            output.classList.add('d-none');
        }, 3000);
    } else {
        form.classList.add('was-validated');
    }
}

function quickRegister(eventType) {
    // Save event type preference
    localStorage.setItem('preferredEventType', eventType);
    
    // Redirect to registration page
    window.location.href = 'register.html';
}

/* Section: Video Control Functions */
function initializeVideoControls() {
    const video = document.getElementById('mainVideo');
    if (!video) return;
    
    // Set initial volume
    video.volume = 0.5;
    
    // Add event listeners
    video.addEventListener('loadeddata', function() {
        console.log('Video data loaded');
    });
    
    video.addEventListener('error', function(e) {
        console.error('Video error:', e);
        document.getElementById('videoStatus').innerHTML = 
            '<span class="badge bg-danger">Video failed to load</span>';
        document.getElementById('videoStatus').classList.remove('d-none');
    });
}

function videoReadyHandler(video) {
    const statusElement = document.getElementById('videoStatus');
    if (statusElement) {
        statusElement.classList.remove('d-none');
        
        // Hide status after 3 seconds
        setTimeout(() => {
            statusElement.classList.add('d-none');
        }, 3000);
    }
    
    console.log('Video ready to play');
}

function changePlaybackSpeed() {
    const video = document.getElementById('mainVideo');
    const speedSelect = document.getElementById('playbackSpeed');
    
    if (video && speedSelect) {
        video.playbackRate = parseFloat(speedSelect.value);
    }
}

function changeVolume() {
    const video = document.getElementById('mainVideo');
    const volumeControl = document.getElementById('volumeControl');
    
    if (video && volumeControl) {
        video.volume = volumeControl.value / 100;
    }
}

function skipBackward() {
    const video = document.getElementById('mainVideo');
    if (video) {
        video.currentTime = Math.max(0, video.currentTime - 10);
    }
}

function skipForward() {
    const video = document.getElementById('mainVideo');
    if (video) {
        video.currentTime = Math.min(video.duration, video.currentTime + 10);
    }
}

function togglePlayPause() {
    const video = document.getElementById('mainVideo');
    if (video) {
        if (video.paused) {
            video.play();
        } else {
            video.pause();
        }
    }
}

function toggleFullscreen() {
    const video = document.getElementById('mainVideo');
    if (video) {
        if (document.fullscreenElement) {
            document.exitFullscreen();
        } else {
            video.requestFullscreen();
        }
    }
}

function loadVideo(type) {
    // Simulate loading different videos based on type
    const video = document.getElementById('mainVideo');
    if (video) {
        // In a real application, you would load different video sources
        video.currentTime = 0;
        video.play();
        
        // Scroll to video section
        video.scrollIntoView({ behavior: 'smooth' });
    }
}

function likeVideo() {
    const likeButton = document.querySelector('[onclick="likeVideo()"]');
    const likeCount = document.getElementById('likeCount');
    
    if (likeCount) {
        let count = parseInt(likeCount.textContent);
        count++;
        likeCount.textContent = count;
        
        // Save to localStorage
        localStorage.setItem('videoLikes', count.toString());
        
        // Update button state
        if (likeButton) {
            likeButton.classList.add('btn-primary');
            likeButton.classList.remove('btn-outline-primary');
        }
    }
}

function shareVideo() {
    if (navigator.share) {
        navigator.share({
            title: 'Community Event Video',
            text: 'Check out this amazing community event!',
            url: window.location.href
        });
    } else {
        // Fallback: copy to clipboard
        navigator.clipboard.writeText(window.location.href).then(() => {
            alert('Video link copied to clipboard!');
        });
    }
}

/* Section: Filter Functions */
function filterEvents() {
    const typeFilter = document.getElementById('eventTypeFilter');
    const startDate = document.getElementById('startDate');
    const endDate = document.getElementById('endDate');
    
    if (!typeFilter) return;
    
    let filteredEvents = eventData;
    
    // Filter by type
    if (typeFilter.value) {
        filteredEvents = filteredEvents.filter(event => event.type === typeFilter.value);
    }
    
    // Filter by date range
    if (startDate.value) {
        filteredEvents = filteredEvents.filter(event => event.date >= startDate.value);
    }
    
    if (endDate.value) {
        filteredEvents = filteredEvents.filter(event => event.date <= endDate.value);
    }
    
    // Update display
    displayFilteredEvents(filteredEvents);
}

function displayFilteredEvents(events) {
    const container = document.getElementById('eventsContainer');
    if (!container) return;
    
    container.innerHTML = '';
    
    if (events.length === 0) {
        container.innerHTML = `
            <div class="col-12 text-center">
                <div class="alert alert-info">
                    <i class="bi bi-info-circle me-2"></i>
                    No events found matching your criteria.
                </div>
            </div>
        `;
        return;
    }
    
    events.forEach(event => {
        const eventCard = createEventCard(event);
        container.appendChild(eventCard);
    });
}

/* Section: Utility Functions */
function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', {
        weekday: 'long',
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    });
}

function loadUserPreferences() {
    // Load saved location
    const savedLocation = localStorage.getItem('userLocation');
    if (savedLocation) {
        const location = JSON.parse(savedLocation);
        const locationSection = document.getElementById('location');
        const locationDisplay = document.getElementById('locationDisplay');
        
        if (locationSection && locationDisplay) {
            locationDisplay.innerHTML = `
                <strong>Last known location:</strong> ${location.lat}, ${location.lon}
            `;
            locationSection.classList.remove('d-none');
        }
    }
    
    // Load video likes
    const savedLikes = localStorage.getItem('videoLikes');
    if (savedLikes) {
        const likeCount = document.getElementById('likeCount');
        if (likeCount) {
            likeCount.textContent = savedLikes;
        }
    }
}

function addSmoothScrolling() {
    // Add smooth scrolling to all anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });
}

/* Section: Keyboard Event Handlers */
document.addEventListener('keydown', function(event) {
    // Handle keyboard shortcuts for video controls
    if (document.getElementById('mainVideo')) {
        switch(event.key) {
            case ' ':
                event.preventDefault();
                togglePlayPause();
                break;
            case 'ArrowLeft':
                event.preventDefault();
                skipBackward();
                break;
            case 'ArrowRight':
                event.preventDefault();
                skipForward();
                break;
            case 'f':
                event.preventDefault();
                toggleFullscreen();
                break;
        }
    }
});

/* Section: Error Handling */
window.addEventListener('error', function(event) {
    console.error('JavaScript error:', event.error);
    
    // Show user-friendly error message for critical errors
    if (event.error.message.includes('network') || event.error.message.includes('fetch')) {
        const errorAlert = document.createElement('div');
        errorAlert.className = 'alert alert-warning alert-dismissible fade show position-fixed';
        errorAlert.style.cssText = 'top: 20px; right: 20px; z-index: 9999; max-width: 400px;';
        errorAlert.innerHTML = `
            <i class="bi bi-exclamation-triangle me-2"></i>
            <strong>Connection Error:</strong> Some features may not work properly.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;
        
        document.body.appendChild(errorAlert);
        
        // Auto-remove after 5 seconds
        setTimeout(() => {
            errorAlert.remove();
        }, 5000);
    }
});

/* Section: Performance Monitoring */
window.addEventListener('load', function() {
    // Log page load performance
    if (window.performance && window.performance.timing) {
        const loadTime = window.performance.timing.loadEventEnd - window.performance.timing.navigationStart;
        console.log(`Page loaded in ${loadTime}ms`);
        
        // Store performance data
        localStorage.setItem('lastLoadTime', loadTime.toString());
    }
});
