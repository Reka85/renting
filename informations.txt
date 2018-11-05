README

 * * MAJOR BUGS / MISSING FEATURES * *

authentication and sign-up not appropriate
 => see configuration/SecurityConfig for login name and pw
 => vehicle edit, delete buttons visible on vehicle's page for all signed-in users, not just for the owner
 => bookings on a user's page are visible for all signed-in users, not just for the owner
 => VehicleDetailsController's submitVehicleForm method doesn't save user/id => error when trying to navigate to modified vehicles page
 
missing controller and service tests, some test cases not working and are marked with 'not working' comment