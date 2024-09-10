import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import LogInForm from '../components/LogInForm';
import { useDispatch } from 'react-redux';
import { setUser } from '../redux/userSlice';
import { useSelector } from 'react-redux';
import { RootState } from '../redux/store';

const LogIn: React.FC = () => {
  const userId = useSelector((state: RootState) => state.user.id);
  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();
  const dispatch = useDispatch(); 

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
      const token = result.token;
      console.log(token); 
      localStorage.setItem('token', token); 
    
      // Then, fetch the user details using the token
      const userResponse = await fetch(`http://localhost:8080/users/me`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
          "Accept": "application/json",
        },
      });
      console.log(userResponse); 

      if (!userResponse.ok) {
        throw new Error('Failed to fetch user details');
      }

      const user = await userResponse.json();
      console.log(user); // Log the user object for debugging

      // Dispatch the user information to the Redux store
      dispatch(setUser(user));

      // Redirect the user to the dashboard or home page after successful login
      navigate('/dashboard');
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
