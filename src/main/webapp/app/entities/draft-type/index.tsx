import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftType from './draft-type';
import DraftTypeDetail from './draft-type-detail';
import DraftTypeUpdate from './draft-type-update';
import DraftTypeDeleteDialog from './draft-type-delete-dialog';

const DraftTypeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftType />} />
    <Route path="new" element={<DraftTypeUpdate />} />
    <Route path=":id">
      <Route index element={<DraftTypeDetail />} />
      <Route path="edit" element={<DraftTypeUpdate />} />
      <Route path="delete" element={<DraftTypeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftTypeRoutes;
