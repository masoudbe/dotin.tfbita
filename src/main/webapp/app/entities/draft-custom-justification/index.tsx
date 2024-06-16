import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftCustomJustification from './draft-custom-justification';
import DraftCustomJustificationDetail from './draft-custom-justification-detail';
import DraftCustomJustificationUpdate from './draft-custom-justification-update';
import DraftCustomJustificationDeleteDialog from './draft-custom-justification-delete-dialog';

const DraftCustomJustificationRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftCustomJustification />} />
    <Route path="new" element={<DraftCustomJustificationUpdate />} />
    <Route path=":id">
      <Route index element={<DraftCustomJustificationDetail />} />
      <Route path="edit" element={<DraftCustomJustificationUpdate />} />
      <Route path="delete" element={<DraftCustomJustificationDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftCustomJustificationRoutes;
