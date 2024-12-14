![Header image](/assets/GITHUB%20REPO%20README%20HEADER.png)

# Project Setup Instructions

## Create and Activate Virtual Environment (Optional)

A virtual environment ensures that your project dependencies are isolated from the global system.

```bash
# Create virtual environment
python -m venv env

# Activate virtual environment
# Windows
.\env\Scripts\activate

# macOS/Linux
source env/bin/activate
```

---

## Install Project Dependencies

Run the following command to install all dependencies:

```bash
pip install -r requirements.txt
```

---

## Download Credential File (`credentials.json`)

1. Log in to [Google Cloud Console](https://console.cloud.google.com).
2. Navigate to:
   - **IAM & Admin** > **Service Accounts** > **Key Management**
3. Select or create a new service account:
   - Click **Create Service Account** if no service account exists.
   - After creating the account, choose **Key** > **Add Key** > **JSON**.
4. The `credentials.json` file will be downloaded to your computer.

---

## Specify the Location of `credentials.json`

Save the `credentials.json` file in an easily accessible location.

Example:

```
C:\Users\YourName\credentials.json
```

---

## Set Environment Variables

Google Cloud uses the `GOOGLE_APPLICATION_CREDENTIALS` environment variable to locate the credential file.

### For Windows (Command Prompt):

1. Open Command Prompt.
2. Run the following command:

```cmd
set GOOGLE_APPLICATION_CREDENTIALS="C:\path\to\credentials.json"
```

Example:

```cmd
set GOOGLE_APPLICATION_CREDENTIALS="C:\Users\YourName\credentials.json"
```

### For Windows (PowerShell):

1. Open PowerShell.
2. Run the following command:

```powershell
$env:GOOGLE_APPLICATION_CREDENTIALS="C:\path\to\credentials.json"
```

### For Linux/macOS:

1. Open a terminal.
2. Run the following command:

```bash
export GOOGLE_APPLICATION_CREDENTIALS="/path/to/credentials.json"
```

Example:

```bash
export GOOGLE_APPLICATION_CREDENTIALS="/home/yourname/credentials.json"
```

---

## Run the Application

Once the environment variable is set, you can run your Python application:

```bash
python run.py
```

---

## Important Notes

- Environment variables are only active during the terminal session.
  - If you close the terminal, you must reset the environment variable.
- **Do not share the `credentials.json` file with others.**
