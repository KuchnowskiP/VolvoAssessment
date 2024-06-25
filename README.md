# Drive IT 2024 - Java for Volvo Group Digital & IT

This is a Spring Boot application that exposes a RESTful API for weather forecasts. It uses the [WeatherAPI](https://www.weatherapi.com/) service to retrieve weather data and provides forecasts for the next three days for various cities. This project is part of the Drive IT 2024 - Java assessment for Volvo Group Digital & IT.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Presentation](#presentation)

## Features

- Fetches weather data from WeatherAPI
- Provides weather forecasts for the next three days
- Includes data such as:
    - Maximum temperature (Celsius)
    - Minimum temperature (Celsius)
    - Average temperature (Celsius)
    - Maximum wind speed (km/h)
    - Total precipitation (mm)
    - Total snowfall (cm)
    - Average humidity (%)
    - Average visibility (km)
    - UV Index
- Exposes a generic RESTful API endpoint
- Generates Swagger/Open API documentation

## API Endpoints

The application exposes only one API endpoint for all cities:

- GET /api/v1/weather/city?cityName={cityName}

The endpoint returns the weather forecast for the next three days for a specific city. The cityName parameter is a query parameter that specifies the city for which you want to get the forecast.

## Installation

To set up the project locally, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/KuchnowskiP/VolvoAssessment.git
   cd VolvoAssessment
2. **Configure WeatherAPI credentials**:
   - Sign up at [WeatherAPI](https://www.weatherapi.com/) and get your API key.
   - Set your API key as an environment variable:
     - Linux/macOS:
       ```bash
       export WEATHER_API_KEY=YOUR_API_KEY
     - Windows:
        ```cmd
       setx WEATHER_API_KEY "YOUR_API_KEY"
       
3. **Build the project**:
    ```bash
    ./gradlew build

4. **Run the application**:
    ```bash
    ./gradlew bootRun

## Usage
Once the application is running, you can access the API documentation at http://localhost:8080/swagger-ui.html.
From there, you can test the API endpoints and see the available operations.

### Example API Call
You can also make API calls using cURL.
To get the weather forecast for the next three days for a specific city, you can make a GET request to the following endpoint, replacing cityName with the desired city:

```bash
    curl -X GET "http://localhost:8080/api/v1/weather/city?cityName={cityName}" -H "accept: application/json"
```
This will return a JSON response with the weather forecast for the specified city and will work on Unix based systems as well as on Windows.

Please replace `{cityName}` with the actual city name in the curl command.

## Presentation
The weather forecast data for the five largest cities in Poland is stored in the following files:
- city_forecasts/warsaw.json
- city_forecasts/krakow.json
- city_forecasts/lodz.json
- city_forecasts/wroclaw.json
- city_forecasts/poznan.json

## Error Handling
As an example of error handling, the application includes a custom exceptions package with three custom exceptions which respond to specific errors from the WeatherAPI service (https://www.weatherapi.com/docs/#intro-error-codes)
- DisabledApiKeyException

Responds to the error code 2008.
It is thrown when the API key is disabled or invalid.
When controller catches this exception, it returns a 401 Unauthorized response with a custom response body. (ApiErrorResponse record)

- LocationNotProvidedException

Responds to the error code 1003.
It is thrown when the cityName path variable is not provided in the request.
When controller catches this exception, it returns a 400 Bad Request response with a custom response body. (ApiErrorResponse record)

- LocationNotFoundException

Responds to the error code 1006.
It is thrown when the specified city is not found in the weather forecast data.
When controller catches this exception, it returns a 404 Not Found response with a custom response body. (ApiErrorResponse record)

## Testing
The application includes unit tests that verify the behavior of the CityWeatherController class.
These test use the Spring Boot testing framework and mock the CityWeatherService with Mockito to isolate the controller logic.

The tests cover the following scenarios:
- When the city name is not provided, the controller should return a BAD_REQUEST (HTTP 400) status code.- Error handling for a API key exception
- When the city name is provided but does not exist, the controller should return a NOT_FOUND (HTTP 404) status code.
- When the API key is not provided, the controller should return an UNAUTHORIZED (HTTP 401) status code.
- When the city name is provided and exists, the controller should return an OK (HTTP 200) status code.