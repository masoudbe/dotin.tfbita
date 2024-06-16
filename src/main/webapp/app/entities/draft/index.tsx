import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Draft from './draft';
import DraftDetail from './draft-detail';
import DraftUpdate from './draft-update';
import DraftDeleteDialog from './draft-delete-dialog';

const DraftRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Draft />} />
    <Route path="new" element={<DraftUpdate />} />
    <Route path=":id">
      <Route index element={<DraftDetail />} />
      <Route path="edit" element={<DraftUpdate />} />
      <Route path="delete" element={<DraftDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftRoutes;
