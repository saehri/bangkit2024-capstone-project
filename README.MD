![Header image](/assets/GITHUB%20REPO%20README%20HEADER.png)

# Divaskin: Personalized Skincare Recommendation App

Divaskin is a mobile application designed to empower users by providing personalized skincare solutions. Built using cutting-edge technologies, it leverages Machine Learning (ML) to classify skin types and recommend tailored skincare products while promoting local skincare brands.

---

## Project Overview

### Team Information

- **Team ID:** C242-PS387
- **Selected Theme/Case:** Economics Empowerment: Navigating Sustainable Economies for All
- **Mentors:**
  - Nuikita Wachid (Mentoring on Saturday, 23 November 2024)
  - Marya Batubara (Mentoring on Saturday, 30 November 2024)

### Team Members

| Member ID    | Name                          | University                       | Role               | Status |
| ------------ | ----------------------------- | -------------------------------- | ------------------ | ------ |
| M459B4KY2725 | Muhammad Amaradipta           | Universitas Nusa Putra           | Machine Learning   | Active |
| M459B4KY4000 | Saepul Bahri                  | Universitas Nusa Putra           | Machine Learning   | Active |
| M459B4KY0303 | Akmal Nuur Fauzan             | Universitas Nusa Putra           | Machine Learning   | Active |
| C452B4KY0025 | Abdul Hafidz Alghifari        | Universitas Muhammadiyah Jakarta | Cloud Computing    | Active |
| C009B4KY2982 | Muhammad Nurma Hidayah        | Universitas Gunadarma            | Cloud Computing    | Active |
| A452B4KY2811 | Muhammad Faiz Amanullah       | Universitas Muhammadiyah Jakarta | Mobile Development | Active |
| A452B4KY3734 | Reihan Aditya Permata Bintang | Universitas Muhammadiyah Jakarta | Mobile Development | Active |

---

## Background

Divaskin addresses the challenges of selecting suitable skincare products by helping users identify their skin type through a CNN-based classification model. The app provides personalized product recommendations, supporting the local economy by promoting local skincare brands.

### Key Features:

- **Skin Type Classification:** CNN model classifies skin types (oily, dry, normal).
- **Ingredient Recommendations:** Suggests suitable skincare ingredients based on skin type.
- **Scalability:** Built with TensorFlow and Google Cloud Platform (GCP) for efficient deployment.
- **Sustainability:** Promotes local skincare brands, aligning with eco-friendly practices.

---

## Tech Stack

### Android Stack

- Developed using Kotlin for a smooth, intuitive user experience.
- TensorFlow Lite ensures real-time model inference on mobile devices.
- APK includes:
  - Main feature workflow implementation.
  - API integration for skin analysis.
  - Networking calls to interact with project APIs.
  - Custom app icon.

### Machine Learning Stack

- CNN model built with TensorFlow for accurate skin type classification.
- Training data sourced from [Jenis Kulit Dataset](https://universe.roboflow.com/gunawan-selayar-xjkcv/jenis-kulit-wajah-k0oyf).

### Cloud Computing Stack

- **Backend Services:** Hosted on Google App Engine with autoscaling.
- **APIs:**
  - `/upload`: Accepts image uploads for analysis.
  - `/predict`: Classifies skin type and provides recommendations.
  - `/delete`: Deletes stored user data.
- **Storage:** Google Cloud Storage handles image file storage securely.

---

## Deployment

<!-- - **Deployed Links:**
  - Upload Endpoint: [https://divaskin-bangkit-capstone.et.r.appspot.com/upload](https://divaskin-bangkit-capstone.et.r.appspot.com/upload)
  - Predict Endpoint: [https://divaskin-bangkit-capstone.et.r.appspot.com/predict](https://divaskin-bangkit-capstone.et.r.appspot.com/predict)
  - Delete Endpoint: [https://divaskin-bangkit-capstone.et.r.appspot.com/delete](https://divaskin-bangkit-capstone.et.r.appspot.com/delete) -->

- **Prototype:** [Figma Design (Access is limited to team members only; Please ask us for access to the document)](https://www.figma.com/design/e49oSrOXCFfIfpyCjlmY19/SUKOZ-UI%2FUX-DESIGN?node-id=238-1070&node-type=frame&t=ds9D0eObdtWLPm36-0)

<!-- - **GitHub Repository:** [https://github.com/saehri/bangkit2024-capstone-project](https://github.com/saehri/bangkit2024-capstone-project) -->

- **10-Minute Video Presentation:** [YouTube Video](https://youtu.be/5Y4mkM_DPQ0)

- **Slides:** [Divaskin Presentation](https://www.figma.com/slides/2c7jbcmP6nK8yc57BF2KlK/DIVASKIN-ORESENTATIONS?node-id=1-1325&node-type=slide&t=pRxHF4O9SVHK8JX3-0)

---

## Go-to-Market Proposal

### Target Market

- **Age Range:** 15 years old and up.
- **Profession:** Any
- **Attributes:**
  - People who wan't to get skincare recommendation based on their skin type.
  - Skincare brand who want to advertise their product to the right audience.

### Marketing Strategy

- Partner with skincare brands for exclusive product recommendations.
- Leverage government initiatives promoting sustainable consumption.

---

## SWOT Analysis

| **Strengths**                                   | **Weaknesses**                                                    |
| ----------------------------------------------- | ----------------------------------------------------------------- |
| Predict skin type using photo uploads/captures. | Dependence on accurate user inputs for effective recommendations. |

| **Opportunities**                                              | **Threats**                                 |
| -------------------------------------------------------------- | ------------------------------------------- |
| Partnerships with eco-friendly brands and government programs. | Competition from established skincare apps. |

---

## Acknowledgments

The success of Divaskin is attributed to the dedication of the development team, the guidance of mentors, and the support from the local community and stakeholders.
