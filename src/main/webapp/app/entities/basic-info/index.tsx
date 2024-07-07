import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import BasicInfo from './basic-info';
import BasicInfoDetail from './basic-info-detail';
import BasicInfoUpdate from './basic-info-update';
import BasicInfoDeleteDialog from './basic-info-delete-dialog';

const BasicInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<BasicInfo />} />
    <Route path="new" element={<BasicInfoUpdate />} />
    <Route path=":id">
      <Route index element={<BasicInfoDetail />} />
      <Route path="edit" element={<BasicInfoUpdate />} />
      <Route path="delete" element={<BasicInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default BasicInfoRoutes;
