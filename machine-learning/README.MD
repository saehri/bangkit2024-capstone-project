![Header image](/assets/GITHUB%20REPO%20README%20HEADER.png)

---

## Our Machine Learning Architecture

- The architecture is based on a convolutional neural network (CNN) using a pre-trained MobileNet model as the base.
- The model is structured as a sequential model, which includes:
- A base model (MobileNet) for feature extraction.
- Additional layers such as:
  - A convolutional layer (Conv2D) with 32 filters and a kernel size of 3.
  - A global average pooling layer (GlobalAveragePooling2D).
  - A dense layer with a softmax activation function for classification into three categories (dry, normal, oily).

---

## Tools and Library Used

- TensorFlow/Keras: The primary library used for building and training the model.
- ImageDataGenerator: For data augmentation and preprocessing of images.
- Matplotlib: For visualizing training progress and results.
- ImageIO: For reading images.

---

## How to Run the Code

- Set Up Your Environment: Ensure you have Python installed, along with the necessary libraries. You can use a package manager like pip to install TensorFlow, Keras, NumPy, Pandas, and Matplotlib.
- Clone the Repository: If our code is hosted on a platform like GitHub, clone the repository to your local machine.
- Prepare Your Dataset: Make sure to download the dataset from the Roboflow platform and place it in the specified directory as outlined in the code.
- Run the Jupyter Notebook: Open the Jupyter Notebook file provided in the repository. You can run each cell sequentially to execute the code.
- Make Predictions: After training the model, you can upload images to test the model’s predictions on skin types.
- Visualize Results: Use the provided visualization code to see how well the model performs and to analyze the results.
