import { createSlice, PayloadAction } from '@reduxjs/toolkit';

export enum Role {
  USER = 'USER',
  ADMIN = 'ADMIN',
}

interface UserState {
  id: number | null;
  username: string;
  email: string;
  role: Role | null;
}

const initialState: UserState = {
  id: null,
  username: '',
  email: '',
  role: null,
};

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    setUser: (state, action: PayloadAction<UserState>) => {
      state.id = action.payload.id;
      state.username = action.payload.username;
      state.email = action.payload.email;
      state.role = action.payload.role;
    },
    clearUser: (state) => {
      state.id = null;
      state.username = '';
      state.email = '';
      state.role = null;
    },
  },
});

export const { setUser, clearUser } = userSlice.actions;
export default userSlice.reducer;
