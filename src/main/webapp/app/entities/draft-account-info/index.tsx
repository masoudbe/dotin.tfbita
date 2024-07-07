import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftAccountInfo from './draft-account-info';
import DraftAccountInfoDetail from './draft-account-info-detail';
import DraftAccountInfoUpdate from './draft-account-info-update';
import DraftAccountInfoDeleteDialog from './draft-account-info-delete-dialog';

const DraftAccountInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftAccountInfo />} />
    <Route path="new" element={<DraftAccountInfoUpdate />} />
    <Route path=":id">
      <Route index element={<DraftAccountInfoDetail />} />
      <Route path="edit" element={<DraftAccountInfoUpdate />} />
      <Route path="delete" element={<DraftAccountInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftAccountInfoRoutes;
