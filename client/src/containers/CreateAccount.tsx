import CreateAccountForm from '../components/CreateAccountForm'; 
import React, {useState} from 'react'; 
import { useDispatch } from 'react-redux';
import { setUser } from '../redux/userSlice';
import { useNavigate } from 'react-router-dom';


const CreateAccount: React.FC = () => {
  const [email, setEmail] = useState<string>('')
  const [password, setPassword] = useState<string>(''); 
  const [error, setError] = useState<string | null>(null); 
  const navigate = useNavigate();
  const dispatch = useDispatch(); 

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const crendentials = {email, password}; 

    try{
      const response = await fetch('http://localhost:8080/auth/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(crendentials), 
      });

      if(!response.ok){
        throw new Error('Registration request failed'); 
      }

      const result = await response.json();
      const token = result.token; 
      localStorage.setItem('token', token); 

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
      console.log(user);

      dispatch(setUser(user));
      navigate('/dashboard');

    } catch (error) {
      console.error('Error:', error);
      setError('Failed to create account, please try again later'); 
    }
  }
  return (
    <CreateAccountForm
    email={email}
    setEmail={setEmail}
    password={password}
    setPassword={setPassword}
    handleSubmit={handleSubmit}
    error={error}
    />
  );
}

export default CreateAccount; 