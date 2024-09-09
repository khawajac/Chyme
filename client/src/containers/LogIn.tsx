import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import LogInForm from '../components/LogInForm';

const LogIn: React.FC = () => {
  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const credentials = { email, password };

    try {
      const response = await fetch('http://localhost:8080/auth/authenticate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(credentials),
      });

      if (!response.ok) {
        throw new Error('Authentication failed');
      }

      const result = await response.json();
      console.log(result); 

      // Assuming the token is returned as "token" in the response
      const token = result.token;

      // Save the token to localStorage
      localStorage.setItem('token', token);
      console.log(token); 

      // Redirect the user to the dashboard or home page after successful login
      navigate('/dashboard'); // Adjust the route as needed
    } catch (error) {
      console.error('Error:', error);
      setError('Failed to sign in. Please check your credentials and try again.');
    }
  };

  return (
    <LogInForm
      email={email}
      setEmail={setEmail}
      password={password}
      setPassword={setPassword}
      handleSubmit={handleSubmit}
      error={error}
    />
  );
};

export default LogIn;
